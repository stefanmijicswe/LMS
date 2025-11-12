package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class Examination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int points;
	
	@Column(nullable = true, length = 200)
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "student_on_year_id", nullable = false)
    private StudentOnYear studentOnYear;
	
	@ManyToOne
	@JoinColumn(name = "knowledge_evaluation_id", nullable = false)
	private KnowledgeEvaluation knowledgeEvaluation;

	public Examination() {}

	public Examination(Long id, int points, String note, StudentOnYear studentOnYear,
			KnowledgeEvaluation knowledgeEvaluation) {
		super();
		this.id = id;
		this.points = points;
		this.note = note;
		this.studentOnYear = studentOnYear;
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public StudentOnYear getStudentOnYear() {
		return studentOnYear;
	}

	public void setStudentOnYear(StudentOnYear studentOnYear) {
		this.studentOnYear = studentOnYear;
	}

	public KnowledgeEvaluation getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(KnowledgeEvaluation knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

}
