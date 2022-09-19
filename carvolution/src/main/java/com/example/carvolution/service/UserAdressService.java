package com.example.carvolution.service;

import com.example.carvolution.exception.NotFoundException;
import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.repository.AdressRepository;
import com.example.carvolution.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdressService {

    UserRepository userRepository;
    AdressRepository adressRepository;

    public UserAdressService(UserRepository userRepository, AdressRepository adressRepository) {
        this.userRepository = userRepository;
        this.adressRepository = adressRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found in db"));
    }

    public User saveUser(User user) {
        Adress adress = user.getAdress();
        Adress checked = checkAdressForDublicates(adress);
        user.setAdress(checked);
        userRepository.save(user);
        return user;
    }

    public Adress updateAdress(Adress adress, int user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User with ID " + user_id + " not found"));
        Adress checkedAdress = checkAdressForDublicates(adress);
        user.setAdress(checkedAdress);
        userRepository.save(user);
        return checkedAdress;
    }

    private Adress checkAdressForDublicates(Adress adress) {
        List<Adress> similarAdresses = adressRepository.findByCityEqualsAndStreetEquals(adress.getCity(), adress.getStreet());
        if (similarAdresses.size() == 0) {
            return adressRepository.save(adress);
        } else {
            return adressRepository.findById(similarAdresses.get(0).getId())
                    .orElseThrow(() -> new NotFoundException("Adress with ID" + similarAdresses.get(0).getId() + " not found"));
        }
    }
}
