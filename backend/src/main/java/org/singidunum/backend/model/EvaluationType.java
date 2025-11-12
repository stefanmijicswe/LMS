package org.singidunum.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class EvaluationType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "evaluationType")
    private List<KnowledgeEvaluation> knowledgeEvaluation = new ArrayList<>();

	public EvaluationType() {}

	public EvaluationType(Long id, String name, List<KnowledgeEvaluation> knowledgeEvaluation) {
		super();
		this.id = id;
		this.name = name;
		this.knowledgeEvaluation = knowledgeEvaluation;
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

	public List<KnowledgeEvaluation> getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(List<KnowledgeEvaluation> knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

	
	

}
