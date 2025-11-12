package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class SubjectAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int finalGrade;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
	
	@ManyToOne
    @JoinColumn(name = "subject_realisation_id")
    private SubjectRealisation subjectRealisation;

	public SubjectAttendance() {}

	public SubjectAttendance(Long id, int finalGrade, Student student, SubjectRealisation subjectRealisation) {
		super();
		this.id = id;
		this.finalGrade = finalGrade;
		this.student = student;
		this.subjectRealisation = subjectRealisation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SubjectRealisation getSubjectRealisation() {
		return subjectRealisation;
	}

	public void setSubjectRealisation(SubjectRealisation subjectRealisation) {
		this.subjectRealisation = subjectRealisation;
	}
	
	
	
	
}
