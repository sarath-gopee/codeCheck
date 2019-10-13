package com.sarath.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


public class tripData {

	@Autowired
	private Location startPoint;
	@Autowired
	private Location endPoint;
	private Double tripFare;
	private Double tripDistance;
	private Date startTime;
	private Date endTime;
	
	public tripData(Location startPoint, Location endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;	
	}
	
	public Location getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Location startPoint) {
		this.startPoint = startPoint;
	}
	public Location getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Location endPoint) {
		this.endPoint = endPoint;
	}
	public Double getTripFare() {
		return tripFare;
	}
	public void setTripFare(Double tripFare) {
		this.tripFare = tripFare;
	}
	public double getTripDistance() {
		return tripDistance;
	}
	public void setTripDistance(Double tripDistance) {
		this.tripDistance = tripDistance;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
