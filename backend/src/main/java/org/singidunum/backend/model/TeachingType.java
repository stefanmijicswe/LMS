package org.singidunum.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class TeachingType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "teachingType")
	private List<TeachingSession> teachingSession;
	
	@OneToMany(mappedBy = "teachingType")
	private List<TeacherInCourse> teachersInCourse;

	public TeachingType() {}

	public TeachingType(Long id, String name, List<TeachingSession> teachingSession,
			List<TeacherInCourse> teachersInCourse) {
		super();
		this.id = id;
		this.name = name;
		this.teachingSession = teachingSession;
		this.teachersInCourse = teachersInCourse;
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

	public List<TeachingSession> getTeachingSession() {
		return teachingSession;
	}

	public void setTeachingSession(List<TeachingSession> teachingSession) {
		this.teachingSession = teachingSession;
	}

	public List<TeacherInCourse> getTeachersInCourse() {
		return teachersInCourse;
	}

	public void setTeachersInCourse(List<TeacherInCourse> teachersInCourse) {
		this.teachersInCourse = teachersInCourse;
	}

	
	
	
	
	
	
	
}
