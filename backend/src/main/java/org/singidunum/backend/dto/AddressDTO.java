package org.singidunum.backend.dto;

public class AddressDTO {
    private Long id;
    private String streetName;
    private String streetNumber;
    private PlaceDTO place;

    public AddressDTO(Long id, String streetName, String streetNumber, PlaceDTO place) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.place = place;
    }

    public AddressDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }
}