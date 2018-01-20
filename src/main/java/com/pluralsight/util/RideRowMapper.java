package com.pluralsight.util;

import com.pluralsight.model.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


//This is a rowMapper that connects the fields of a ride Java Object with a row in the db
public class RideRowMapper implements RowMapper<Ride> {

    @Override
    public Ride mapRow(ResultSet resultSet, int i) throws SQLException {
        Ride ride = new Ride();
        ride.setId(resultSet.getInt("id"));
        ride.setName(resultSet.getString("name"));
        ride.setDuration(resultSet.getInt("duration"));

        return ride;
    }
}
