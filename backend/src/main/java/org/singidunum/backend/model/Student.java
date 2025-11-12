package org.singidunum.backend.model;


import java.util.List;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Student() {}

	public Student(Long id, String name, String surname, String pin, Address address,
			List<SubjectAttendance> subjectAttendance, List<StudentOnYear> studentOnYears) {
		super();
		this.id = id;
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