package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, unique = true, length = 13)
    private String pin;

    @Column(nullable = false, length = 1500)
    private String biography;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String surname;

    @OneToOne(mappedBy = "rector")
    private University university;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    private StudyProgramme studyProgramme;

    @OneToOne
    private Faculty faculty;

    @OneToOne
    private TeacherInCourse teacherInCourse;

    public Teacher() {
    }

    public Teacher(Long id, User user, String pin, String biography, String name, String surname, University university,
                   Address address, StudyProgramme studyProgramme, Faculty faculty, TeacherInCourse teacherInCourse) {
        this.id = id;
        this.user = user;
        this.pin = pin;
        this.biography = biography;
        this.name = name;
        this.surname = surname;
        this.university = university;
        this.address = address;
        this.studyProgramme = studyProgramme;
        this.faculty = faculty;
        this.teacherInCourse = teacherInCourse;
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

    public StudyProgramme getStudyProgramme() {
        return studyProgramme;
    }

    public void setStudyProgramme(StudyProgramme studyProgramme) {
        this.studyProgramme = studyProgramme;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public TeacherInCourse getTeacherInCourse() {
        return teacherInCourse;
    }

    public void setTeacherInCourse(TeacherInCourse teacherInCourse) {
        this.teacherInCourse = teacherInCourse;
    }
}
