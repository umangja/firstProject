package com.umang.dao;

import java.util.List;

import com.umang.model.Feedback;

public interface Feedbackdao {

	void add(Feedback fb);

	List<Feedback> getAllIncompletedFeedback();

	void setComplete(int id);

}
