package com.example.carvolution.service;

import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.repository.AdressRepository;
import com.example.carvolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdressRepository adressRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(int id){
        return userRepository.findById(id);
    }

    public User saveUserAndAdress(User user){
        Adress adress = user.getAdress();
        Adress checked = checkAdress(adress);
        user.setAdress(checked);
        return userRepository.save(user);
    }

    public User updateAdress(Adress adress, int user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new EntityNotFoundException("Id not found"));
        Adress checked = checkAdress(adress);
        user.setAdress(checked);
        return userRepository.save(user);
    }

    private Adress checkAdress(Adress adress){
        List<Adress> similar = adressRepository.findByCityEqualsAndStreetEquals(adress.getCity(), adress.getStreet());
        if (similar.size() == 0){
            return adressRepository.save(adress);
        } else {
            return adressRepository.findById(similar.get(0).getId()).orElseThrow(() -> new EntityNotFoundException("Id not found"));
        }
    }
}
