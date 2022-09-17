package com.example.carvolution.controller;

import com.example.carvolution.model.Adress;
import com.example.carvolution.service.CrudService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carvolution")
public class AdressController {

    @Autowired
    CrudService crudService;

    @PutMapping ("/adress/{user_id}")
    private void updateAdress(@PathVariable int user_id, @RequestBody Adress adress){
        crudService.updateAdress(adress, user_id);
    }
}
