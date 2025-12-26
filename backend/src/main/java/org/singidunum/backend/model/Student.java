package org.singidunum.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 13)
    private String pin;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany
    private List<SubjectAttendance> subjectAttendance;

    @OneToMany
    private List<StudentOnYear> studentOnYears;

    public Student() {
    }

    public Student(Long id, User user, String name, String surname, String pin, Address address,
                   List<SubjectAttendance> subjectAttendance, List<StudentOnYear> studentOnYears) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.pin = pin;
        this.address = address;
        this.subjectAttendance = subjectAttendance;
        this.studentOnYears = studentOnYears;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<SubjectAttendance> getSubjectAttendance() {
        return subjectAttendance;
    }

    public void setSubjectAttendance(List<SubjectAttendance> subjectAttendance) {
        this.subjectAttendance = subjectAttendance;
    }

    public List<StudentOnYear> getStudentOnYears() {
        return studentOnYears;
    }

    public void setStudentOnYears(List<StudentOnYear> studentOnYears) {
        this.studentOnYears = studentOnYears;
    }
}
