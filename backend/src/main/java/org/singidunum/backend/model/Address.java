package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String streetName;

    @Column(nullable = false, length = 50)
    private String streetNumber;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id", nullable = false)
    private Place place;

    @OneToOne(mappedBy = "address")
    private University university;

    @OneToMany(mappedBy = "address")
    private List<Student> students;

    public Address(Long id, String streetName, String streetNumber, Place place, University university, List<Student> students) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.place = place;
        this.university = university;
        this.students = students;
    }

    public Address() {}

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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
