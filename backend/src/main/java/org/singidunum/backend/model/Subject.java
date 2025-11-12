package org.singidunum.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Subject {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(nullable = false)
	 private String name;
	 
	 @Column(nullable = false)
	 private int ects;
	 
	 @Column(nullable = false)
	 private boolean mandatory;
	 
	 @Column(nullable = false)
	 private int numberOfLectures;
	 
	 @Column(nullable = false)
	 private int numberOfExercises;
	 
	 @Column(nullable = true)
	 private int otherTeachingTypes;
	 
	 @Column(nullable = true)
	 private int research;
	 
	 @Column(nullable = true)
	 private int otherClasses;
	 
	 @ManyToOne
	 @JoinColumn(name = "prerequisite_id")
	 private Subject prerequisite;

	 @OneToMany(mappedBy = "prerequisite")
	 private List<Subject> dependentSubjects;
	 
	 @ManyToOne
	 @JoinColumn(name = "year_of_study_id")
	 private YearOfStudy yearOfStudy;
	 
	 @OneToMany(mappedBy = "subject")
	 private List<Outcome> outcomes;
	 
	 @OneToMany(mappedBy = "subject")
	 private List<SubjectRealisation> subjectRealisation;

	 public Subject() {}

	 public Subject(Long id, String name, int ects, boolean mandatory, int numberOfLectures, int numberOfExercises,
			int otherTeachingTypes, int research, int otherClasses, Subject prerequisite,
			List<Subject> dependentSubjects, YearOfStudy yearOfStudy, List<Outcome> outcomes,
			List<SubjectRealisation> subjectRealisation) {
		super();
		this.id = id;
		this.name = name;
		this.ects = ects;
		this.mandatory = mandatory;
		this.numberOfLectures = numberOfLectures;
		this.numberOfExercises = numberOfExercises;
		this.otherTeachingTypes = otherTeachingTypes;
		this.research = research;
		this.otherClasses = otherClasses;
		this.prerequisite = prerequisite;
		this.dependentSubjects = dependentSubjects;
		this.yearOfStudy = yearOfStudy;
		this.outcomes = outcomes;
		this.subjectRealisation = subjectRealisation;
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

	 public int getEcts() {
		 return ects;
	 }

	 public void setEcts(int ects) {
		 this.ects = ects;
	 }

	 public boolean isMandatory() {
		 return mandatory;
	 }

	 public void setMandatory(boolean mandatory) {
		 this.mandatory = mandatory;
	 }

	 public int getNumberOfLectures() {
		 return numberOfLectures;
	 }

	 public void setNumberOfLectures(int numberOfLectures) {
		 this.numberOfLectures = numberOfLectures;
	 }

	 public int getNumberOfExercises() {
		 return numberOfExercises;
	 }

	 public void setNumberOfExercises(int numberOfExercises) {
		 this.numberOfExercises = numberOfExercises;
	 }

	 public int getOtherTeachingTypes() {
		 return otherTeachingTypes;
	 }

	 public void setOtherTeachingTypes(int otherTeachingTypes) {
		 this.otherTeachingTypes = otherTeachingTypes;
	 }

	 public int getResearch() {
		 return research;
	 }

	 public void setResearch(int research) {
		 this.research = research;
	 }

	 public int getOtherClasses() {
		 return otherClasses;
	 }

	 public void setOtherClasses(int otherClasses) {
		 this.otherClasses = otherClasses;
	 }

	 public Subject getPrerequisite() {
		 return prerequisite;
	 }

	 public void setPrerequisite(Subject prerequisite) {
		 this.prerequisite = prerequisite;
	 }

	 public List<Subject> getDependentSubjects() {
		 return dependentSubjects;
	 }

	 public void setDependentSubjects(List<Subject> dependentSubjects) {
		 this.dependentSubjects = dependentSubjects;
	 }

	 public YearOfStudy getYearOfStudy() {
		 return yearOfStudy;
	 }

	 public void setYearOfStudy(YearOfStudy yearOfStudy) {
		 this.yearOfStudy = yearOfStudy;
	 }

	 public List<Outcome> getOutcomes() {
		 return outcomes;
	 }

	 public void setOutcomes(List<Outcome> outcomes) {
		 this.outcomes = outcomes;
	 }

	 public List<SubjectRealisation> getSubjectRealisation() {
		 return subjectRealisation;
	 }

	 public void setSubjectRealisation(List<SubjectRealisation> subjectRealisation) {
		 this.subjectRealisation = subjectRealisation;
	 }
	 
}
