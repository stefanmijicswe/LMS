package org.singidunum.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class EvaluationInstrument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "knowledge_evaluation_id")
    private KnowledgeEvaluation knowledgeEvaluation;
	
	@OneToMany(mappedBy = "evaluationInstrument")
    private List<File> file = new ArrayList<>();

	public EvaluationInstrument() {}

	public EvaluationInstrument(Long id, KnowledgeEvaluation knowledgeEvaluation, List<File> file) {
		super();
		this.id = id;
		this.knowledgeEvaluation = knowledgeEvaluation;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KnowledgeEvaluation getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(KnowledgeEvaluation knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}
	
	

}
