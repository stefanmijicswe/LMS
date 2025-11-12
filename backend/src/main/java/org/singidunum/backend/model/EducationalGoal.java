package org.singidunum.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class EducationalGoal {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(nullable = false)
	 private String description;
	 
	 @ManyToMany(mappedBy = "educationalGoal")
	 private List<Outcome> outcome;
	 
	 public EducationalGoal() {}

	 public EducationalGoal(Long id, String description, List<Outcome> outcome) {
		super();
		this.id = id;
		this.description = description;
		this.outcome = outcome;
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

	 public List<Outcome> getOutcome() {
		 return outcome;
	 }

	 public void setOutcome(List<Outcome> outcome) {
		 this.outcome = outcome;
	 } 
	
}
