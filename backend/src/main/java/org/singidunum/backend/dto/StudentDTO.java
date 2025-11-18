package org.singidunum.backend.dto;

public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private String pin;

    public StudentDTO(Long id, String name, String surname, String pin, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pin = pin;
        this.address = address;
    }

    private AddressDTO address;

    public StudentDTO(){};

    public Long getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
