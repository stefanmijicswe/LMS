package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class TeacherInCourse {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private int numberOfClasses;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
    private Teacher teacher;

	@ManyToOne
    @JoinColumn(name = "subject_realisation_id")
    private SubjectRealisation subjectRealisation;
	
	 @ManyToOne
	 @JoinColumn(name = "teaching_type_id")
	 private TeachingType teachingType;
	 
	 public TeacherInCourse() {
		super();
	}

	 public TeacherInCourse(Long id, int numberOfClasses, Teacher teacher, SubjectRealisation subjectRealisation,
			TeachingType teachingType) {
		super();
		this.id = id;
		this.numberOfClasses = numberOfClasses;
		this.teacher = teacher;
		this.subjectRealisation = subjectRealisation;
		this.teachingType = teachingType;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public int getNumberOfClasses() {
		 return numberOfClasses;
	 }

	 public void setNumberOfClasses(int numberOfClasses) {
		 this.numberOfClasses = numberOfClasses;
	 }

	 public Teacher getTeacher() {
		 return teacher;
	 }

	 public void setTeacher(Teacher teacher) {
		 this.teacher = teacher;
	 }

	 public SubjectRealisation getSubjectRealisation() {
		 return subjectRealisation;
	 }

	 public void setSubjectRealisation(SubjectRealisation subjectRealisation) {
		 this.subjectRealisation = subjectRealisation;
	 }

	 public TeachingType getTeachingType() {
		 return teachingType;
	 }

	 public void setTeachingType(TeachingType teachingType) {
		 this.teachingType = teachingType;
	 }
	 
	 
} 
	 