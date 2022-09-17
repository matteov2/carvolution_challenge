package com.example.carvolution.repository;

import com.example.carvolution.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdressRepository extends JpaRepository<Adress, Integer> {

    List<Adress> findByCityEqualsAndStreetEquals(String city, String street);
}
