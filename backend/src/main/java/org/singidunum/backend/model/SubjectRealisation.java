package org.singidunum.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class SubjectRealisation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@OneToMany(mappedBy = "subjectRealisation")
	private List<TeacherInCourse> teachersInCourse;
	
	@OneToMany(mappedBy = "subjectRealisation")
	private List<TeachingSession> teachingSession;
	 
	@OneToMany(mappedBy = "subjectRealisation")
	private List<KnowledgeEvaluation> knowledgeEvaluation;

	@OneToMany(mappedBy = "subjectRealisation")
	private List<SubjectAttendance> subjectAttendance;

	public SubjectRealisation() {}

	public SubjectRealisation(Long id, Subject subject, List<TeacherInCourse> teachersInCourse,
			List<TeachingSession> teachingSession, List<KnowledgeEvaluation> knowledgeEvaluation,
			List<SubjectAttendance> subjectAttendance) {
		super();
		this.id = id;
		this.subject = subject;
		this.teachersInCourse = teachersInCourse;
		this.teachingSession = teachingSession;
		this.knowledgeEvaluation = knowledgeEvaluation;
		this.subjectAttendance = subjectAttendance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<TeacherInCourse> getTeachersInCourse() {
		return teachersInCourse;
	}

	public void setTeachersInCourse(List<TeacherInCourse> teachersInCourse) {
		this.teachersInCourse = teachersInCourse;
	}

	public List<TeachingSession> getTeachingSession() {
		return teachingSession;
	}

	public void setTeachingSession(List<TeachingSession> teachingSession) {
		this.teachingSession = teachingSession;
	}

	public List<KnowledgeEvaluation> getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(List<KnowledgeEvaluation> knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

	public List<SubjectAttendance> getSubjectAttendance() {
		return subjectAttendance;
	}

	public void setSubjectAttendance(List<SubjectAttendance> subjectAttendance) {
		this.subjectAttendance = subjectAttendance;
	}
	
	
	
	
}
