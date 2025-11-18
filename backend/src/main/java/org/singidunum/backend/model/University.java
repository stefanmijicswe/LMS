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

    @OneToMany(mappedBy = "university")
    private List<Faculty> faculties;

    @Column(nullable = true, length = 1000)
    private String description;

    @Column(nullable = true, length = 20)
    private String phoneNumber;

    @Column(nullable = true, length = 100)
    private String officialEmail;

    @Column(nullable = true, length = 100)
    private String website;

    public University(Long id, String name, LocalDateTime foundingDate, Address address, Teacher rector, List<Faculty> faculties, String description, String phoneNumber, String officialEmail, String website) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
        this.address = address;
        this.rector = rector;
        this.faculties = faculties;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.officialEmail = officialEmail;
        this.website = website;
    }



    public University(Long id, String name, LocalDateTime foundingDate, Address address, Teacher rector, List<Faculty> faculties, String description) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
        this.address = address;
        this.rector = rector;
        this.faculties = faculties;
        this.description = description;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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