package com.server_labs.server_lab_1.controllers;

import com.server_labs.server_lab_1.models.Role;
import com.server_labs.server_lab_1.models.Role_User;
import com.server_labs.server_lab_1.models.User;
import com.server_labs.server_lab_1.repo.RoleRepository;
import com.server_labs.server_lab_1.repo.Role_User_Repository;
import com.server_labs.server_lab_1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Role_User_Repository role_user_repository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String login, @RequestParam String password, Model model) {
        User userFromDb = userRepository.findByLogin(login);
        if (userFromDb != null) {
            model.addAttribute("message", "Такой логин уже существует!");
            return "registration";
        }
        User user = new User(login, password);
        user.setActive(true);
        userRepository.save(user);

        userFromDb = userRepository.findByLogin(login);
        Role roleFromDb = roleRepository.findByName("USER");
        Role_User role_user = new Role_User(roleFromDb, userFromDb);
        role_user_repository.save(role_user);
        return "redirect:/login";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
}
