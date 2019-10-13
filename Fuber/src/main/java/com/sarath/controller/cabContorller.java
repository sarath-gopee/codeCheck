package com.sarath.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sarath.DAO.cabDAO;
import com.sarath.model.Location;
import com.sarath.model.cabData;
import com.sarath.services.cabFunctionServices;
import com.sarath.services.userFunctionServices;

@RestController
public class cabContorller {
	Logger logger = LogManager.getLogger(); 
	@Autowired
	private cabFunctionServices servicesObj;
	@Autowired
	private userFunctionServices Obj;
	
	@PostMapping(path="/start-trip")
	private ResponseEntity<Object> startRide(@RequestHeader(name = "cabid", required = true) Integer cabid) {
		
		String returnMsg = "Started the trip";
		cabData bookedCab = cabDAO.allCabs.get(cabid);
		
		servicesObj.startTheTrip(bookedCab);
		return new ResponseEntity(returnMsg,HttpStatus.OK);
		
	}
	
	
	@PostMapping(path="/end-trip")
	private ResponseEntity<Object> endRide(@RequestHeader(name = "cabid", required = true) Integer cabid) {
		
		cabData releaseCar = cabDAO.allCabs.get(cabid);

		releaseCar = servicesObj.endTheTrip(releaseCar);
		
		servicesObj.releaseCarBooking(releaseCar);
		
		return new ResponseEntity<>(releaseCar,HttpStatus.OK);
	} 
	

	
	
}
