package org.singidunum.backend.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Title {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private Date electionDate;
	
	@Column(nullable = false)
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "scientific_field_id")
	ScientificField scientificField;
	
	@ManyToOne
	@JoinColumn(name = "title_type_id")
	TitleType titleType;
	
	public Title() {}

	public Title(Long id, Date electionDate, Date endDate, ScientificField scientificField, TitleType titleType) {
		super();
		this.id = id;
		this.electionDate = electionDate;
		this.endDate = endDate;
		this.scientificField = scientificField;
		this.titleType = titleType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ScientificField getScientificField() {
		return scientificField;
	}

	public void setScientificField(ScientificField scientificField) {
		this.scientificField = scientificField;
	}

	public TitleType getTitleType() {
		return titleType;
	}

	public void setTitleType(TitleType titleType) {
		this.titleType = titleType;
	}
	
	
	

}
