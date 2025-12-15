package com.hms.services;

import java.util.List;

import com.hms.entities.Feedback;

public interface FeedbackService {

	Feedback saveFeedback(Feedback feedback);

	List<Feedback> getAllFeedbacks();

}
