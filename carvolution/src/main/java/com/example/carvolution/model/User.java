package com.example.carvolution.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstname;
    private String lastname;

    @ManyToOne
    //@JoinColumn(name = "adress_id",referencedColumnName = "adress_id", nullable = false)
    private Adress adress;

    public User(){}

    public User(String firstname, String lastname, Adress adress) {
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
