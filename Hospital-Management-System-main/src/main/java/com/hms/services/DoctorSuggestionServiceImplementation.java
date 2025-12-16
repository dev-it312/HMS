package com.hms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.repositories.DiseaseRepository;
import com.hms.entities.Disease;

import java.util.List;

@Service  // Ensure this annotation is present
public class DoctorSuggestionServiceImplementation implements DoctorSuggestionService {  // Rename to DoctorSuggestionServiceImpl

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override  // Mark the overridden method
    public String getDoctorSuggestion(String inputSymptoms) {
        String[] inputSymptomArray = inputSymptoms.toLowerCase().split(",\\s*");

        List<Disease> diseases = diseaseRepository.findAll();

        Disease bestMatch = null;
        int maxMatches = 0;

        for (Disease disease : diseases) {
            String[] diseaseSymptoms = disease.getSymptoms().toLowerCase().split(",\\s*");
            int matchCount = 0;

            for (String symptom : inputSymptomArray) {
                for (String diseaseSymptom : diseaseSymptoms) {
                    if (diseaseSymptom.contains(symptom)) {
                        matchCount++;
                    }
                }
            }

            if (matchCount > maxMatches) {
                maxMatches = matchCount;
                bestMatch = disease;
            }
        }

        if (bestMatch != null) {
            return "You may have " + bestMatch.getName() + ". Please consult a " + bestMatch.getSpecialty() + ".";
        } else {
            return "We couldn't determine a specific disease. Please consult a General Doctor.";
        }
    }
}
