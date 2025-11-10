package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String pin;

    @Column(nullable = false, length = 1500)
    private String biography;

    @Column(length =  100, nullable = false)
    private String name;

    @Column(length =  100, nullable = false)
    private String surname;

    @OneToOne(mappedBy = "rector")
    private University university;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Teacher(Long id, String pin, String biography, String name, String surname, University university, Address address) {
        this.id = id;
        this.pin = pin;
        this.biography = biography;
        this.name = name;
        this.surname = surname;
        this.university = university;
        this.address = address;
    }

    public Teacher() {}

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
