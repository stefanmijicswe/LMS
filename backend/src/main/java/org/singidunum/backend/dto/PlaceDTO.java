package org.singidunum.backend.dto;

public class PlaceDTO {
    private Long id;
    private String name;
    private CountryDTO country;


    public PlaceDTO(Long id, String name, CountryDTO country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public PlaceDTO() {}

    public Long getId() {
        return id;
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }
}
