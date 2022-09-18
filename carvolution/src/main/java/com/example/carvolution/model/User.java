package com.example.carvolution.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Adress adress;

    public User(){}

    public User(String firstname, String lastname, Adress adress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
    }

    public User(int id,String firstname, String lastname, Adress adress) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
