package org.singidunum.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class YearOfStudy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	private LocalDate endDate;
	
	@Column(nullable = false)
	private Integer yearNumber;
    
    @ManyToOne
    @JoinColumn(name = "study_programme_id")
    private StudyProgramme studyProgramme;

    @OneToMany(mappedBy = "yearOfStudy")
    private List<Subject> subjects;
    
    @ManyToOne
    @JoinColumn(name = "student_on_year_id")
    private StudentOnYear studentOnYear;

	public YearOfStudy() {}

	public YearOfStudy(Long id, LocalDate startDate, LocalDate endDate, Integer yearNumber, StudyProgramme studyProgramme, List<Subject> subjects,
			StudentOnYear studentOnYear) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.yearNumber = yearNumber;
		this.studyProgramme = studyProgramme;
		this.subjects = subjects;
		this.studentOnYear = studentOnYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public StudyProgramme getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(StudyProgramme studyProgramme) {
		this.studyProgramme = studyProgramme;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public StudentOnYear getStudentOnYear() {
		return studentOnYear;
	}

	public void setStudentOnYear(StudentOnYear studentOnYear) {
		this.studentOnYear = studentOnYear;
	}

	public Integer getYearNumber() {
		return yearNumber;
	}

	public void setYearNumber(Integer yearNumber) {
		this.yearNumber = yearNumber;
	}

	
}
