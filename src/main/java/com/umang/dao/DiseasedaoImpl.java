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

import com.umang.model.Disease;


public class DiseasedaoImpl implements Diseasedao {

	
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public DiseasedaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public DiseasedaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void addDisease(Disease disease) {
		String sql = "insert into Diseases(name,how_rare,danger_level) values(?,?,?)";
		jdbcTemplate.update(sql,new Object[] {disease.getName(),disease.getHow_rare(),disease.getDanger_level()});
	}
	
	public List<Disease> getAllDiseasesIdAndName() {
		String sql  = "select id,name from Diseases";
		List<Disease> dis = jdbcTemplate.query(sql,new ResultSetExtractor<List<Disease> >() {
			
			public List<Disease> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Disease> meds = new ArrayList<Disease> ();
				while(rs.next()) {;
					Disease med = new Disease();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return dis;
	}
	
	
	public List<Disease> getAllDiseases() {
		String sql  = "select * from Diseases";
		List<Disease> diss = jdbcTemplate.query(sql,new ResultSetExtractor<List<Disease> >() {
			
			public List<Disease> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Disease> diss = new ArrayList<Disease> ();
				while(rs.next()) {;
					Disease dis = new Disease();
					dis.setName(rs.getString("name"));
					dis.setId(rs.getInt("id"));
					dis.setHow_rare(rs.getInt("how_rare"));
					dis.setDanger_level(rs.getInt("danger_level"));
					diss.add(dis);
				}
				return diss;
			}
			
		});
		return diss;
	}
	
	
	

}
