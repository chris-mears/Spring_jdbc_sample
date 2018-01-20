package com.pluralsight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;
import org.springframework.transaction.annotation.Transactional;

@Service("rideService")
public class RideServiceImpl implements RideService {

	@Autowired
	private RideRepository rideRepository;

	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}

	@Override
	public Ride getRide(Integer id) {
		return rideRepository.getRide(id);
	}
	
	@Override
	public List<Ride> getRides() {
		return rideRepository.getRides();
	}

	@Override
	public Ride updateRide(Ride ride) {
		return rideRepository.updateRide(ride);
	}

	@Override
	//This is the annotation that will roll back transactions if errors occur
    @Transactional
	public void batch() {
		List<Ride> rides = rideRepository.getRides();

		List<Object[]> pairs = new ArrayList<>();

		for (Ride ride: rides) {
			Object [] tmp = {new Date(), ride.getId()};
			pairs.add(tmp);
		}

		rideRepository.updateRides(pairs);

		//throws an error so we could test the transaction rollback functionality with spring
		//throw new DataAccessException("Testing Exception Handling") {};
	}

	@Override
	public void deleteRide(Integer id) {
		rideRepository.deleteRide(id);
	}
}
