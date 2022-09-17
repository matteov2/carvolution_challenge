package com.example.carvolution.model;

import javax.persistence.*;

@Entity
@Table(name = "adresses")
public class Adress {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String street;
    private String postcode;
    private String city;

    public Adress(){}

    public Adress(String street, String postcode, String city) {
        this.street = street;
        this.postcode = postcode;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
