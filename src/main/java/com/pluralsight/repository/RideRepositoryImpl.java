package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pluralsight.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	//This is how the repository connects to the db
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//Route to create a ride object in db
	@Override
	public Ride createRide(Ride ride) {
		//jdbcTemplate.update("insert into ride (name, duration) VALUES (?,?)", ride.getName(), ride.getDuration());

		//This allows us to create the ride and then return that value as a java object
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			@Override
//			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//
//				PreparedStatement ps = connection.prepareStatement("insert into ride (name, duration) VALUES (?,?)", new String [] {"id"});
//				ps.setString(1, ride.getName());
//				ps.setInt(2, ride.getDuration());
//				return ps;
//			}
//		}, keyHolder);
//
//		Number id = keyHolder.getKey();

		//Another way to create a ride and return the ride object from the db
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		insert.setGeneratedKeyName("id");

		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());

		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");

		insert.setTableName("ride");
		insert.setColumnNames(columns);
		Number id = insert.executeAndReturnKey(data);

		return getRide(id.intValue());
	}


	//Route to get one ride from db
	public Ride getRide(Integer id) {
		Ride ride = jdbcTemplate.queryForObject("SELECT * FROM ride WHERE id = ?", new RideRowMapper(), id);

		return ride;
	}

	//Route to get all Rides from the db
	@Override
	public List<Ride> getRides() {

		List<Ride> rides = jdbcTemplate.query("SELECt * FROM ride", new RideRowMapper());

		return rides;
	}
	
}
