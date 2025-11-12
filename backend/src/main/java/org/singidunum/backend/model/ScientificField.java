package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ScientificField {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "scientificField")
	private List<Title> titles;

	public ScientificField() {}

	public ScientificField(Long id, String name, List<Title> titles) {
		super();
		this.id = id;
		this.name = name;
		this.titles = titles;
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

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}
	
	
	
	

	

}
