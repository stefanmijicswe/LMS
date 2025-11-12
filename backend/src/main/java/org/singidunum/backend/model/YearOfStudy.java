package org.singidunum.backend.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class YearOfStudy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date year;
    
    @ManyToOne
    @JoinColumn(name = "study_programme_id")
    private StudyProgramme studyProgramme;

    @OneToMany(mappedBy = "yearOfStudy")
    private List<Subject> subjects;
    
    @ManyToOne
    @JoinColumn(name = "student_on_year_id")
    private StudentOnYear studentOnYear;

	public YearOfStudy() {}

	public YearOfStudy(Long id, Date year, StudyProgramme studyProgramme, List<Subject> subjects,
			StudentOnYear studentOnYear) {
		super();
		this.id = id;
		this.year = year;
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

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
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

	
}
