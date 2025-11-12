package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TitleType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titleName;
	
	@OneToMany(mappedBy = "titleType")
	private List<Title> title;
	
	public TitleType() {}

	public TitleType(Long id, String titleName, List<Title> title) {
		super();
		this.id = id;
		this.titleName = titleName;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public List<Title> getTitle() {
		return title;
	}

	public void setTitle(List<Title> title) {
		this.title = title;
	}
	
	

	

}
