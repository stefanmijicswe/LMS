package org.singidunum.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Faculty {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToOne
	@JoinColumn(name = "dean_id")
	private Teacher dean;
	
	@OneToMany(mappedBy = "faculty")
	private List<StudyProgramme> studyProgramme;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	private University university;

    @Column(nullable = true, length = 1000)
    private String description;

    @Column(nullable = true, length = 100)
    private String website;

    @Column(nullable = true, length = 20)
    private String phoneNumber;

    @Column(nullable = true, length = 100)
    private String officialEmail;

	public Faculty() {}

	public Faculty(Long id, String name, Address address, Teacher dean, List<StudyProgramme> studyProgramme,
			University university) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dean = dean;
		this.studyProgramme = studyProgramme;
		this.university = university;
	}

    public Faculty(Long id, String name, Address address, Teacher dean, List<StudyProgramme> studyProgramme, University university, String description, String website, String phoneNumber, String officialEmail) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dean = dean;
        this.studyProgramme = studyProgramme;
        this.university = university;
        this.description = description;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.officialEmail = officialEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Teacher getDean() {
		return dean;
	}

	public void setDean(Teacher dean) {
		this.dean = dean;
	}

	public List<StudyProgramme> getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(List<StudyProgramme> studyProgramme) {
		this.studyProgramme = studyProgramme;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

}
