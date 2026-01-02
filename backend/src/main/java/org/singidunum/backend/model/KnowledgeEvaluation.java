package org.singidunum.backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class KnowledgeEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date startTime;
	
	@Column(nullable = false)
	private Date endTime;
	
	@Column(nullable = false)
	private int points;
	
	@ManyToOne
	@JoinColumn(name = "evaluation_type_id")
    private EvaluationType evaluationType;

	@ManyToOne
	@JoinColumn(name = "subject_realisation_id")
	private SubjectRealisation subjectRealisation;
	
	@OneToMany(mappedBy = "knowledgeEvaluation")
    private List<Outcome> outcomes;
	
	@OneToMany(mappedBy = "knowledgeEvaluation", cascade = CascadeType.ALL)
    private List<Examination> examinations;

	@OneToMany(mappedBy = "knowledgeEvaluation")
	private List<EvaluationInstrument> evaluationInstrument;

    @ManyToOne
    @JoinColumn(name = "examination_period_id")
    private ExaminationPeriod examinationPeriod;

	public KnowledgeEvaluation() {}

	public KnowledgeEvaluation(Long id, Date startTime, Date endTime, int points, EvaluationType evaluationType,
			SubjectRealisation subjectRealisation, List<Outcome> outcomes, List<Examination> examinations, List<EvaluationInstrument> evaluationInstrument,
			ExaminationPeriod examinationPeriod) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.points = points;
		this.evaluationType = evaluationType;
		this.subjectRealisation = subjectRealisation;
		this.outcomes = outcomes;
		this.examinations = examinations;
		this.evaluationInstrument = evaluationInstrument;
		this.examinationPeriod = examinationPeriod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public EvaluationType getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(EvaluationType evaluationType) {
		this.evaluationType = evaluationType;
	}

	public SubjectRealisation getSubjectRealisation() {
		return subjectRealisation;
	}

	public void setSubjectRealisation(SubjectRealisation subjectRealisation) {
		this.subjectRealisation = subjectRealisation;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public List<EvaluationInstrument> getEvaluationInstrument() {
		return evaluationInstrument;
	}

	public void setEvaluationInstrument(List<EvaluationInstrument> evaluationInstrument) {
		this.evaluationInstrument = evaluationInstrument;
	}

	public ExaminationPeriod getExaminationPeriod() {
		return examinationPeriod;
	}

	public void setExaminationPeriod(ExaminationPeriod examinationPeriod) {
		this.examinationPeriod = examinationPeriod;
	}
}