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

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;
import com.umang.model.Locationnew;
import com.umang.model.User;

public class LocationdaoImpl implements Locationdao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public LocationdaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public LocationdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void addLocation(Locationnew loc) {
		String sql = "INSERT INTO Locations(column_no,floor_no,room_no,row_no) values(?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {loc.getColumn_no(),loc.getFloor_no(),loc.getRoom_no(),loc.getRow_no()});
		
	}
	
	public List<Locationnew> getAllLocation() {
		String sql = "select * from Locations";
		List<Locationnew> locations =jdbcTemplate.query(sql, new ResultSetExtractor< List<Locationnew> >() {
			
			public List<Locationnew> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Locationnew> loc = new ArrayList<Locationnew> ();
				while(rs.next()) {
					Locationnew location = new Locationnew();
					location.setColumn_no(rs.getInt("column_no"));
					location.setFloor_no(rs.getInt("floor_no"));
					location.setId(rs.getInt("id"));
					location.setRoom_no(rs.getInt("room_no"));
					location.setRow_no(rs.getInt("row_no"));
					loc.add(location);
				}
				return loc;
			}
			
		});
		return locations;
	}
	
	public List<Locationnew> getLocationofMedicine(int medicine_id) {
		String sql = "select Locations.id as id,column_no,floor_no,room_no,row_no from Locations,Medicines where Medicines.id=? and Medicines.location_id=Locations.id";
		List<Locationnew> locations =jdbcTemplate.query(sql,new Object[] {medicine_id} ,new ResultSetExtractor< List<Locationnew> >() {
			
			public List<Locationnew> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Locationnew> loc = new ArrayList<Locationnew> ();
				while(rs.next()) {
					Locationnew location = new Locationnew();
					location.setColumn_no(rs.getInt("column_no"));
					location.setFloor_no(rs.getInt("floor_no"));
					location.setId(rs.getInt("id"));
					location.setRoom_no(rs.getInt("room_no"));
					location.setRow_no(rs.getInt("row_no"));
					loc.add(location);
				}
				return loc;
			}
			
		});
		return locations;
	}
	
	
	
	

}
