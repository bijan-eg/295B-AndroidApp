package com.example.traveltest3;

public class TravelPackage {
	private int id;
	private String package_name;
	private String description;
	private String start_date;
	private String end_date;
	private String package_type;
	private boolean flight;
	private boolean hotel;
	private boolean insurance;
	private boolean restaurant;
	private boolean local_booking;
	private String status;
	private int ownerid;

	public TravelPackage(){
		this.package_name = "";
		this.description = "";
		this.start_date = "";
		this.end_date = "";
		this.package_type = "";
		this.flight = false;
		this.hotel = false;
		this.insurance = false;
		this.restaurant = false;
		this.local_booking = false;
		this.status = "";
	}
	
	public void packageFlush(){
		this.package_name = "";
		this.description = "";
		this.start_date = "";
		this.end_date = "";
		this.package_type = "";
		this.flight = false;
		this.hotel = false;
		this.insurance = false;
		this.restaurant = false;
		this.local_booking = false;
		this.status = "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String name) {
		this.package_name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String sdate) {
		this.start_date = sdate;
	}
	
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String edate) {
		this.end_date = edate;
	}
	
	public String getPackage_type() {
		return package_type;
	}
	public void setPackage_type(String ptype) {
		this.package_type = ptype;
	}
	
	public boolean getFlight() {
		return flight;
	}
	public void setflight(boolean flight) {
		this.flight = flight;
	}
	
	public boolean getHotel() {
		return hotel;
	}
	public void setHotel(boolean hotel) {
		this.hotel = hotel;
	}
	
	public boolean getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(boolean restaurant) {
		this.restaurant = restaurant;
	}
	
	public boolean getLocalBooking() {
		return local_booking;
	}

	public void setLocalBooking(boolean localB) {
		this.local_booking = localB;
	}

	public boolean getInsurance() {
		return insurance;
	}
	public void setInsurance(boolean insur) {
		this.insurance = insur;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getOwnerId() {
		return ownerid;
	}
	public void setOwnerId(int id) {
		this.ownerid = id;
	}
}