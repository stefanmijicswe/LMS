package org.singidunum.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class TeachingSession {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private LocalDateTime startTime;
	
	@Column(nullable = false)
	private LocalDateTime endTime;
	
	@ManyToOne
	@JoinColumn(name = "teaching_type_id")
    private TeachingType teachingType;

	@OneToMany(mappedBy = "teachingSession")
	private List<Outcome> outcomes;
	
	@ManyToOne
	@JoinColumn(name = "subject_realisation_id")
    private SubjectRealisation subjectRealisation;

	public TeachingSession() {}
	
	public TeachingSession(Long id, LocalDateTime startTime, LocalDateTime endTime, TeachingType teachingType,
			List<Outcome> outcomes, SubjectRealisation subjectRealisation) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teachingType = teachingType;
		this.outcomes = outcomes;
		this.subjectRealisation = subjectRealisation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public TeachingType getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(TeachingType teachingType) {
		this.teachingType = teachingType;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	public SubjectRealisation getSubjectRealisation() {
		return subjectRealisation;
	}

	public void setSubjectRealisation(SubjectRealisation subjectRealisation) {
		this.subjectRealisation = subjectRealisation;
	}
	
	
	
	

	
	
	
}
