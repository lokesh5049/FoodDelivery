package com.sapphireims.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Order Business Object for persistent use
 * Simple POJO class
 * 
 * @author lokesh.yadav
 * @since   2019-01-17
 *
 */

@Entity
@Table(name="orderhistory")
public class OrderBo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false,unique=true)
	private int id;
	@Column(name="location")
	private String location;
	@Column
	private Date date;
	 @ManyToOne(fetch = FetchType.EAGER ,targetEntity=ProductBo.class)
	 @JoinColumn(name = "food_id", nullable = false)
	 private ProductBo foodId;
	 @Column
	 private int number;
	 @Column(name="orderby_id")
	 private String orderbyId;
	 @Column(name="cost")
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
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the foodId
	 */
	public ProductBo getFoodId() {
		return foodId;
	}
	/**
	 * @param foodId the foodId to set
	 */
	public void setFoodId(ProductBo foodId) {
		this.foodId = foodId;
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
		return "OrderBo [id=" + id + ", location=" + location + ", date=" + date + ", foodId=" + foodId + ", number="
				+ number + ", orderbyId=" + orderbyId + ", cost=" + cost + "]";
	}

}
