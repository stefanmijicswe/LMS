package org.singidunum.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class StudyProgramme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "coordinator_id")
	private Teacher coordinator;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	@OneToMany(mappedBy = "studyProgramme")
	private List<YearOfStudy> yearOfStudy;

	public StudyProgramme() {}

	public StudyProgramme(Long id, String name, Teacher coordinator, Faculty faculty, List<YearOfStudy> yearOfStudy) {
		super();
		this.id = id;
		this.name = name;
		this.coordinator = coordinator;
		this.faculty = faculty;
		this.yearOfStudy = yearOfStudy;
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

	public Teacher getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(Teacher coordinator) {
		this.coordinator = coordinator;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<YearOfStudy> getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(List<YearOfStudy> yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

}