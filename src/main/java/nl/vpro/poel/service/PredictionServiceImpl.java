package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;

    @Autowired
    PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public List<Prediction> getPredictions(User user) {
        return predictionRepository.findAllByUser(user);
    }
}
