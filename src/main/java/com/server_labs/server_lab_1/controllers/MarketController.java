package com.server_labs.server_lab_1.controllers;


import com.server_labs.server_lab_1.models.Game;
import com.server_labs.server_lab_1.models.GameInCart;
import com.server_labs.server_lab_1.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
    public class MarketController {
        private List<GameInCart> gameInCartList = new ArrayList<GameInCart>();

        @Autowired
        private GameRepository gameRepository;

        @GetMapping("/market")
        public String marketMain(HttpSession session, Model model) {
            Iterable<Game> games = gameRepository.findAll();
            model.addAttribute("games", games);
            gameInCartList = (List<GameInCart>) session.getAttribute("gameInCartList");
            if (gameInCartList == null) {
                gameInCartList = new ArrayList<GameInCart>();
                session.setAttribute("gameInCartList", gameInCartList);
            }
            return "market";
        }

        @GetMapping("/market/add")
        @PreAuthorize("hasAuthority('ADMIN')")
        public String gameAdd(Model model) {
            return "market-add";
        }

        @PostMapping("/market/add")
        @PreAuthorize("hasAuthority('ADMIN')")
        public String gamePostAdd(@RequestParam String name, @RequestParam double cost, Model model) {
                Game game = new Game(name, cost);
                gameRepository.save(game);
                return "redirect:/market";
        }

        @GetMapping("/market/{id}")
        public String toCart(@PathVariable(value = "id") int id, HttpSession session, Model model) {
            Game game = gameRepository.findById(id).orElseThrow(null);
            if (game != null) {
                for (GameInCart gameInCart : gameInCartList) {
                    if (gameInCart.getGame().getId() == id) {
                        return "redirect:/market";
                    }
                }
                gameInCartList.add(new GameInCart(game, 1));
                session.setAttribute("gameInCartList", gameInCartList);
            }
            return "redirect:/market";
        }

        @GetMapping("/market/{id}/edit")
        @PreAuthorize("hasAuthority('ADMIN')")
        public String gameEdit(@PathVariable(value = "id") int id, Model model) {
            if(!gameRepository.existsById(id)) {
                return "redirect:/market";
            }
            Game game = gameRepository.findById(id).orElseThrow(null);
            model.addAttribute("game", game);
            return "market-edit";
        }

        @PostMapping("/market/{id}/edit")
        @PreAuthorize("hasAuthority('ADMIN')")
        public String gamePostUpdate(@PathVariable(value = "id") int id, @RequestParam String name, @RequestParam double cost, Model model) {
            Game game = gameRepository.findById(id).orElseThrow(null);
            game.setName(name);
            game.setCost(cost);
            gameRepository.save(game);
            return "redirect:/market";
        }

        @PostMapping("/market/{id}/remove")
        @PreAuthorize("hasAuthority('ADMIN')")
        public String gamePostDelete(@PathVariable(value = "id") int id, Model model) {
            Game game = gameRepository.findById(id).orElseThrow(null);
            gameRepository.delete(game);
            return "redirect:/market";
        }

    }
