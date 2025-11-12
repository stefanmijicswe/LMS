package org.singidunum.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Outcome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToMany
	@JoinTable(
	        name = "outcome_educational_goal",
	        joinColumns = @JoinColumn(name = "outcome_id"),
	        inverseJoinColumns = @JoinColumn(name = "educational_goal_id"))
	    private List<EducationalGoal> educationalGoal;
	
	@ManyToOne
	@JoinColumn(name = "teaching_session_id")
	private TeachingSession teachingSession;
	
	@OneToMany(mappedBy = "outcome")
	private List<TeachingMaterial> teachingMaterial = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "knowledge_evaluation_id")
    private KnowledgeEvaluation knowledgeEvaluation;

	@ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

	public Outcome() {}

	public Outcome(Long id, String description, List<EducationalGoal> educationalGoal, TeachingSession teachingSession,
			List<TeachingMaterial> teachingMaterial, KnowledgeEvaluation knowledgeEvaluation, Subject subject) {
		super();
		this.id = id;
		this.description = description;
		this.educationalGoal = educationalGoal;
		this.teachingSession = teachingSession;
		this.teachingMaterial = teachingMaterial;
		this.knowledgeEvaluation = knowledgeEvaluation;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EducationalGoal> getEducationalGoal() {
		return educationalGoal;
	}

	public void setEducationalGoal(List<EducationalGoal> educationalGoal) {
		this.educationalGoal = educationalGoal;
	}

	public TeachingSession getTeachingSession() {
		return teachingSession;
	}

	public void setTeachingSession(TeachingSession teachingSession) {
		this.teachingSession = teachingSession;
	}

	public List<TeachingMaterial> getTeachingMaterial() {
		return teachingMaterial;
	}

	public void setTeachingMaterial(List<TeachingMaterial> teachingMaterial) {
		this.teachingMaterial = teachingMaterial;
	}

	public KnowledgeEvaluation getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(KnowledgeEvaluation knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}