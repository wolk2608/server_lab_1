package com.server_labs.server_lab_1.controllers;

import com.server_labs.server_lab_1.models.GameInCart;
import com.server_labs.server_lab_1.models.Game_Ord;
import com.server_labs.server_lab_1.models.Ord;
import com.server_labs.server_lab_1.models.User;
import com.server_labs.server_lab_1.repo.Game_Ord_Repository;
import com.server_labs.server_lab_1.repo.OrdRepository;
import com.server_labs.server_lab_1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;








@RestController
public class CartController {

    @Autowired
    private HttpSession session;

    private List<GameInCart> gameInCartList;

    @Autowired
    private OrdRepository ordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Game_Ord_Repository game_Ord_Repository;

    @GetMapping("/cart/getList")
    public List<GameInCart> getList(HttpSession session) {
        gameInCartList = (List<GameInCart>) session.getAttribute("gameInCartList");
        if (gameInCartList == null) {
            gameInCartList = new ArrayList<GameInCart>();
            session.setAttribute("gameInCartList", gameInCartList);
        }
        return gameInCartList;
    }

    @GetMapping("/cart/{id}")
    public GameInCart getOne(@PathVariable(value = "id") Integer id) {
        return getGameInCart(id);
    }

    private GameInCart getGameInCart(Integer id) {
        for (GameInCart gameInCart : gameInCartList) {
            if(gameInCart.getGame().getId() == id) {
                return gameInCart;
            }
        }
        return null;
    }

    //Записываем в базу и обнуляемся
    @PostMapping("/cart")
    public void save() {
        gameInCartList = (List<GameInCart>) session.getAttribute("gameInCartList");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName());
        Ord ord = new Ord(user);
        System.out.println("order Id: " + ord.getId() + " ----order user: " + ord.getUser().getId());
        ordRepository.save(ord);
        ord.setId(ordRepository.findByUserOrderByIdDesc(user).getId());
        System.out.println("order Id: " + ord.getId() + " ----order user: " + ord.getUser().getId());
        for (GameInCart gameInCart : gameInCartList) {
            Game_Ord game_ord = new Game_Ord(gameInCart.getGame(), ord, gameInCart.getCount());
            game_Ord_Repository.save(game_ord);
        }
        session.removeAttribute("gameInCartList");
    }

    @PutMapping("/cart/{id}")
    public GameInCart update(@PathVariable(value = "id") Integer id, @RequestBody GameInCart gameInCart) {
        gameInCartList = (List<GameInCart>) session.getAttribute("gameInCartList");

        GameInCart gameFromList = getGameInCart(id);
        if (gameInCart.getCount() == 0) {
            return gameFromList;
        }
        int index = gameInCartList.indexOf(gameFromList);
        gameInCartList.remove(index);
        gameInCartList.add(index, gameInCart);
        session.setAttribute("gameInCartList", gameInCartList);
        return gameInCart;
    }

    @DeleteMapping("/cart/{id}")
    public void delete(@PathVariable(value = "id") Integer id) {
        GameInCart gameFromList = getGameInCart(id);
        gameInCartList.remove(gameFromList);
        session.setAttribute("gameInCartList", gameInCartList);
    }
}
