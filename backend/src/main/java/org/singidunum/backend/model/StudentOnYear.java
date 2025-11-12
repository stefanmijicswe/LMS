package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class StudentOnYear {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date enrolmentDate;
	
	@Column(nullable = false)
	private String recordNumber;
	
	@ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "year_of_study_id")
    private YearOfStudy yearOfStudy;

    @OneToMany(mappedBy = "studentOnYear")
    private List<Examination> examination;

	public StudentOnYear() {}

	public StudentOnYear(Long id, Date enrolmentDate, String recordNumber, Student student, YearOfStudy yearOfStudy,
			List<Examination> examination) {
		super();
		this.id = id;
		this.enrolmentDate = enrolmentDate;
		this.recordNumber = recordNumber;
		this.student = student;
		this.yearOfStudy = yearOfStudy;
		this.examination = examination;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEnrolmentDate() {
		return enrolmentDate;
	}

	public void setEnrolmentDate(Date enrolmentDate) {
		this.enrolmentDate = enrolmentDate;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public YearOfStudy getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(YearOfStudy yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public List<Examination> getExamination() {
		return examination;
	}

	public void setExamination(List<Examination> examination) {
		this.examination = examination;
	}
	
	

}
