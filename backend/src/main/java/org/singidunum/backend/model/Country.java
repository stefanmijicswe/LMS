package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false,  length = 80)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Place> places;

    public Country(Long id, String name, List<Place> places) {
        this.id = id;
        this.name = name;
        this.places = places;
    }

    public Country() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
