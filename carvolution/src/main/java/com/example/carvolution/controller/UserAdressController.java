package com.example.carvolution.controller;

import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.service.UserAdressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carvolution")
public class UserAdressController {

    UserAdressService userAdressService;

    public UserAdressController(UserAdressService userAdressService) {
        this.userAdressService = userAdressService;
    }

    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userAdressService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    private User getUser(@PathVariable int id) {
        return userAdressService.getUser(id);
    }

    @PostMapping("/user")
    private User saveUser(@RequestBody User user) {
        return userAdressService.saveUser(user);
    }

    @PutMapping("/adress/{user_id}")
    private Adress updateAdress(@PathVariable int user_id, @RequestBody Adress adress) {
        return userAdressService.updateAdress(adress, user_id);
    }
}
