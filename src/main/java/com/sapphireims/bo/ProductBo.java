package com.sapphireims.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.JoinColumn;
/**
 * Product Business Object for persistent use
 * Simple POJO class
 * 
 * @author lokesh.yadav
 * @since   2019-01-10 
 *
 */
@Entity
@Table(name="product")
public class ProductBo implements Serializable{
	
	
	private static final long serialVersionUID = -744718773923770018L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false,unique=true)
	private int id;
	@Column(name="name")
	private String name;
	@Column
	private double price;
	@Column(name="resturent_name")
	private String resturentName;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "product_user", catalog = "customer", joinColumns = { 
			@JoinColumn(name = "product_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "user_id", 
					nullable = false, updatable = false) })
	private Set<UsersBo> users=new HashSet<>(0);
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "foodId",targetEntity=OrderBo.class)
	private Set<OrderBo> order=new HashSet<>(0);
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
	 * @return the users
	 */
	public Set<UsersBo> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<UsersBo> users) {
		this.users = users;
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
	/**
	 * @return the order
	 */
	public Set<OrderBo> getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Set<OrderBo> order) {
		this.order = order;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductBo [id=" + id + ", name=" + name + ", price=" + price + ", resturentName=" + resturentName
				+ ", users=" + users + ", order=" + order + "]";
	}

}
