package com.umang.dao;

import java.util.List;

import com.umang.model.Partner;
import com.umang.model.Patient;
import com.umang.model.User;

public interface Patientdao {

	void addPatient(Patient patient);

	public List<Patient> getAll();

	List<Patient> getPatientById(int patient_id);

	List<Patient> getMostValuablePatientSorted();

	List<Integer> getTotalBillOfPatient(int id);


}
