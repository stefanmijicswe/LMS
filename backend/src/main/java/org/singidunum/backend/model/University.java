package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private LocalDateTime foundingDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rector_id")
    private Teacher rector;
    
    @OneToMany
    private List<Faculty> faculties;

    public University() {}

	public University(Long id, String name, LocalDateTime foundingDate, Address address, Teacher rector,
			List<Faculty> faculties) {
		super();
		this.id = id;
		this.name = name;
		this.foundingDate = foundingDate;
		this.address = address;
		this.rector = rector;
		this.faculties = faculties;
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

	public LocalDateTime getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(LocalDateTime foundingDate) {
		this.foundingDate = foundingDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Teacher getRector() {
		return rector;
	}

	public void setRector(Teacher rector) {
		this.rector = rector;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}
    
    

}