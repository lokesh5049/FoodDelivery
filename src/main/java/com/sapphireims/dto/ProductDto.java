/**
 * 
 */
package com.sapphireims.dto;


/**
 *  Product Data Transfer Object for Service use
 * Simple POJO class
 * 
 * @author lokesh.yadav
 * @since   2019-01-11
 *
 */

public class ProductDto {

	private int id;
	private String name;
	private double price;
	private String resturentName;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the resturentName
	 */
	public String getResturentName() {
		return resturentName;
	}

	/**
	 * @param resturentName the resturentName to set
	 */
	public void setResturentName(String resturentName) {
		this.resturentName = resturentName;
	}


}
