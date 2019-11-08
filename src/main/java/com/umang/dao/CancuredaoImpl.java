package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.umang.model.Cancure;
import com.umang.model.Disease;
import com.umang.model.Medicine;

public class CancuredaoImpl implements Cancuredao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public CancuredaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public CancuredaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void addCancure(Cancure cancure) {
		String sql = "insert into Can_cure values(?,?)";
		jdbcTemplate.update(sql,new Object[] {cancure.getMedicine_id(),cancure.getDisease_id()});
		
	}
	
	public int isExisting(String parameter, String parameter2) {
		String sql = "select count(*) as cnt from Can_cure where medicine_id=? and diseases_id=?";
		int ans = jdbcTemplate.query(sql,new Object[] {parameter,parameter2},new ResultSetExtractor<Integer>() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {;
					return rs.getInt("cnt");
				}
				return 0;
			}
			
		});
		return ans;
	}
	
	
	public List<Disease> getDiseasesForMedicine(int id) {
		String sql = "select Diseases.name,Diseases.id,Diseases.how_rare,Diseases.danger_level from Can_cure,Diseases where medicine_id=? and diseases_id=Diseases.id";
		List<Disease> diss = jdbcTemplate.query(sql,new Object[] {id},new ResultSetExtractor<List<Disease> >() {
			
			public List<Disease> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Disease> diss = new ArrayList<Disease> ();
				while(rs.next()) {;
					Disease dis = new Disease();
					dis.setName(rs.getString("name"));
					dis.setId(rs.getInt("id"));
					dis.setDanger_level(rs.getInt("danger_level"));
					dis.setHow_rare(rs.getInt("how_rare"));
					diss.add(dis);
				}
				return diss;
			}
			
		});
		return diss;
	}
	
	public List<Medicine> getMedicineThatCanCure(int dis_id) {
		String sql="select Medicines.name,Medicines.id,Medicines.company,Medicines.in_stock,Medicines.price,Medicines.expiration_date,Medicines.supplier_id,Medicines.location_id from Medicines,Can_cure where  Can_cure.diseases_id=? and Can_cure.medicine_id=Medicines.id";
		List<Medicine> meds = jdbcTemplate.query(sql,new Object[] {dis_id},new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setCompany(rs.getString("company"));
					med.setIn_stock(rs.getInt("in_stock"));
					med.setPrice(rs.getInt("price"));
					med.setExpiration_date(rs.getDate("expiration_date"));
					med.setSupplier_id(rs.getInt("supplier_id"));
					med.setLocation_id(rs.getInt("location_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	
	
}
