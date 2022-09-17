package com.example.carvolution.controller;

import com.example.carvolution.model.User;
import com.example.carvolution.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carvolution")
public class UserController {

    @Autowired
    CrudService crudService;

    @GetMapping("/users")
    private List<User> getAllUsers(){
        return crudService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    private Optional<User> getUser(@PathVariable int id){
        return crudService.getUser(id);
    }

    @PostMapping("/user/save")
    private void saveUserAndAdress(@RequestBody User user){
        crudService.saveUserAndAdress(user);
    }
}
