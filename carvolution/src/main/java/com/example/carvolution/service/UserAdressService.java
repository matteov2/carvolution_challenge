package com.example.carvolution.service;

import com.example.carvolution.exception.NotFoundException;
import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.repository.AdressRepository;
import com.example.carvolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserAdressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdressRepository adressRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(int id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID "+ id + " not found in db"));
    }

    public User saveUserAndAdress(User user){
        Adress adress = user.getAdress();
        Adress checked = checkAdress(adress);
        user.setAdress(checked);
        userRepository.save(user);
        return userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User with ID " + user.getId() + " not found"));
    }

    public Adress updateAdress(Adress adress, int user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("User with ID " + user_id+ " not found"));
        Adress checked = checkAdress(adress);
        user.setAdress(checked);
        userRepository.save(user);
        return checked;
    }

    private Adress checkAdress(Adress adress){
        List<Adress> similar = adressRepository.findByCityEqualsAndStreetEquals(adress.getCity(), adress.getStreet());
        if (similar.size() == 0){
            return adressRepository.save(adress);
        } else {
            return adressRepository.findById(similar.get(0).getId()).orElseThrow(() -> new NotFoundException("Adress with ID" + similar.get(0).getId() +" not found"));
        }
    }
}
