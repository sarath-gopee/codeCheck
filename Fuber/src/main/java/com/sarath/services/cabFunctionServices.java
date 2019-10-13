package com.sarath.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarath.model.Location;
import com.sarath.model.cabData;

@Component
public class cabFunctionServices {
	@Autowired
	private userFunctionServices Obj;
	
	//to set cab destination as cust. destination and set current time as start time
	public void startTheTrip(cabData ridingCab) {
		
		Double cabLat = ridingCab.getUserData().getTripDetails().getStartPoint().getLatitude();
		Double cabLong = ridingCab.getUserData().getTripDetails().getStartPoint().getLongitude();
		Location cabCurrLoc = new Location(cabLat, cabLong);
		
		ridingCab.setCabCurrLocation(cabCurrLoc);
		ridingCab.getUserData().getTripDetails().setStartTime(new Date()); 
	}
	
	//set the endtime and calculate total distance and fare
	public cabData endTheTrip(cabData ridingCab) {
		Double totalDistance, totalFare, pinkValue;Long rideTime ;
		
		ridingCab.getUserData().getTripDetails().setEndTime(new Date());
		
		Location startloc = ridingCab.getUserData().getTripDetails().getStartPoint();
		Location endloc = ridingCab.getUserData().getTripDetails().getEndPoint();
		
		Date startTime = ridingCab.getUserData().getTripDetails().getStartTime();
		Date endTime = new Date();
		totalDistance = Obj.calculateDistance(startloc, endloc);
		rideTime = endTime.getTime() - startTime.getTime();
		if(ridingCab.isPinkColor()) pinkValue = 5.0;
		else pinkValue = 0.0;
		
		totalFare = this.fareCalculator(totalDistance, rideTime.doubleValue(), pinkValue);
		
		ridingCab.getUserData().getTripDetails().setTripDistance(totalDistance);
		ridingCab.getUserData().getTripDetails().setTripFare(totalFare);
		
		return ridingCab;
		
		
	}
	
	//to calculate the total fare
	private Double fareCalculator(Double dist, Double time, Double pinkVal) {
		Double fare;
		fare = (dist * 2 ) + ( time * 1 ) + pinkVal;
		
		return fare;		
	}
	
	
	// to release the car from booking settings
	public void releaseCarBooking(cabData releaseThis) {
		Location endloc = releaseThis.getUserData().getTripDetails().getEndPoint();
		
		//release the cab from booking
		releaseThis.setBookingStatus(false);
		releaseThis.setCarAvailability(true);
		
		//reset cab location
		releaseThis.setCabCurrLocation(endloc);
				
	}
	
}
