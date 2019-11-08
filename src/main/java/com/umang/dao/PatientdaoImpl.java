package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.umang.model.Partner;
import com.umang.model.Patient;

public class PatientdaoImpl implements Patientdao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public PatientdaoImpl() {
		
	}
	
	public PatientdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void addPatient(Patient patient) {
		String sql = "insert into Patients(name,phone_no) values(?,?)";
		jdbcTemplate.update(sql,new Object[] {patient.getName(),patient.getPhone_no()});
		
	}
	
	public List<Patient> getAll() {
		String sql = "select * from Patients";
		List<Patient> meds = jdbcTemplate.query(sql,new ResultSetExtractor<List<Patient> >() {
			
			public List<Patient> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Patient> meds = new ArrayList<Patient> ();
				while(rs.next()) {;
					Patient med = new Patient();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setPhone_no(rs.getString("phone_no"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public List<Patient> getPatientById(int patient_id) {
		String sql = "select * from Patients where id=?";
		List<Patient> meds = jdbcTemplate.query(sql,new Object[] {patient_id},new ResultSetExtractor<List<Patient> >() {
			
			public List<Patient> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Patient> meds = new ArrayList<Patient> ();
				while(rs.next()) {;
					Patient med = new Patient();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setPhone_no(rs.getString("phone_no"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public List<Patient> getMostValuablePatientSorted() {
	    Calendar c = Calendar.getInstance();  
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    Date reqired_date = c.getTime();
		String sql = "select * from Patients where id in (select purchased_by from Bills where purchased_by!='NULL' and dateAndTime>=? group by purchased_by order by sum(total) desc)";
		List<Patient> meds = jdbcTemplate.query(sql,new Object[] {reqired_date},new ResultSetExtractor<List<Patient> >() {
			
			public List<Patient> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Patient> meds = new ArrayList<Patient> ();
				while(rs.next()) {;
					Patient med = new Patient();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setPhone_no(rs.getString("phone_no"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public List<Integer> getTotalBillOfPatient(int id) {
		String sql = "select sum(total) as total from Bills where purchased_by=? group by purchased_by";
		List<Integer> meds = jdbcTemplate.query(sql,new Object[] {id},new ResultSetExtractor<List<Integer> >() {
			
			public List<Integer> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Integer> meds = new ArrayList<Integer> ();
				while(rs.next()) {;
					meds.add(rs.getInt("total"));
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	
	
	
	
	
}
