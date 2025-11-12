package org.singidunum.backend.model;

import jakarta.persistence.*;

@Entity
public class File {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String url;
	
	@ManyToOne
    @JoinColumn(name = "evaluation_instrument_id")
    private EvaluationInstrument evaluationInstrument;
	
	@ManyToOne
	@JoinColumn(name = "teaching_material_id")
	private TeachingMaterial teachingMaterial;

	public File() {}

	public File(Long id, String description, String url, EvaluationInstrument evaluationInstrument,
			TeachingMaterial teachingMaterial) {
		super();
		this.id = id;
		this.description = description;
		this.url = url;
		this.evaluationInstrument = evaluationInstrument;
		this.teachingMaterial = teachingMaterial;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public EvaluationInstrument getEvaluationInstrument() {
		return evaluationInstrument;
	}

	public void setEvaluationInstrument(EvaluationInstrument evaluationInstrument) {
		this.evaluationInstrument = evaluationInstrument;
	}

	public TeachingMaterial getTeachingMaterial() {
		return teachingMaterial;
	}

	public void setTeachingMaterial(TeachingMaterial teachingMaterial) {
		this.teachingMaterial = teachingMaterial;
	}
	
	
	
	
}
