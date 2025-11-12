package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false,  length = 80)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Place(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Place() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
