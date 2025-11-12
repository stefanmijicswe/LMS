package org.singidunum.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class TeachingMaterial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ElementCollection
	@CollectionTable(name = "teaching_material_authors", joinColumns = @JoinColumn(name = "teaching_material_id"))
	@Column(name = "author")
	private List<String> authors = new ArrayList<>();
	
	@Column(nullable = false)
	private Date yearPublished;

	@OneToMany(mappedBy = "teachingMaterial")
	private List<File> file;
	
	@ManyToOne
	@JoinColumn(name = "outcome_id")
	private Outcome outcome;

	public TeachingMaterial() {}

	public TeachingMaterial(Long id, String name, List<String> authors, Date yearPublished, List<File> file,
			Outcome outcome) {
		super();
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.yearPublished = yearPublished;
		this.file = file;
		this.outcome = outcome;
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

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public Date getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Date yearPublished) {
		this.yearPublished = yearPublished;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
	
	

}