package org.singidunum.backend.service;
import org.singidunum.backend.model.EvaluationType;
import org.singidunum.backend.repository.EvaluationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class EvaluationTypeService {

    private final EvaluationTypeRepository evaluationTypeRepository;

    public EvaluationTypeService(EvaluationTypeRepository evaluationTypeRepository) {
        this.evaluationTypeRepository = evaluationTypeRepository;
    }

    public Iterable<EvaluationType> findAll() {
        return evaluationTypeRepository.findAll();
    }
}

