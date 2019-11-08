package com.umang.dao;

import java.util.List;

import com.umang.model.Disease;

public interface Diseasedao {

	public void addDisease(Disease disease);

	public List<Disease> getAllDiseasesIdAndName();

	public List<Disease> getAllDiseases();

}
