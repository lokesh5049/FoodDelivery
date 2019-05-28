package com.sapphireims.dto;

/**
 * Order Data Transfer Object for Service use
 * Simple POJO class
 * 
 * @author lokesh.yadav
 * @since   2019-01-17
 *
 */


public class OrderDto {

	private int id;

	private String location;

	private String date;

	private int foodId;
	private String foodname;

	private int number;
	
	private String resturentname;

	private String orderbyId;
	
	private double cost;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the foodId
	 */
	public int getFoodId() {
		return foodId;
	}

	/**
	 * @param foodId the foodId to set
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	/**
	 * @return the foodname
	 */
	public String getFoodname() {
		return foodname;
	}

	/**
	 * @param foodname the foodname to set
	 */
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the orderbyId
	 */
	public String getOrderbyId() {
		return orderbyId;
	}

	/**
	 * @param orderbyId the orderbyId to set
	 */
	public void setOrderbyId(String orderbyId) {
		this.orderbyId = orderbyId;
	}

	/**
	 * @return the resturentname
	 */
	public String getResturentname() {
		return resturentname;
	}

	/**
	 * @param resturentname the resturentname to set
	 */
	public void setResturentname(String resturentname) {
		this.resturentname = resturentname;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", location=" + location + ", date=" + date + ", foodId=" + foodId + ", foodname="
				+ foodname + ", number=" + number + ", resturentname=" + resturentname + ", orderbyId=" + orderbyId
				+ ", cost=" + cost + "]";
	}

}
