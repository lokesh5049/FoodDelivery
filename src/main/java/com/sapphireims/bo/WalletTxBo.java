/** 
 * Java class
 * 
 */
package com.sapphireims.bo;

import java.io.Serializable;
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
 * @author lokesh.yadav
 *
 * @since Jan 21, 2019
 */
@Entity
@Table(name="tx_history")
public class WalletTxBo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false,unique=true)
	private int id;
	@Column(name="userid")
	private String username;
	@Column
	private double amount ;
	@Column
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY ,targetEntity=UserWalletBo.class)
	@JoinColumn(name = "wall_id")
	private UserWalletBo transection;
	@Column(name="card_no")
	private long cardNo;
	/**
	 * @return the transection
	 */
	public UserWalletBo getTransection() {
		return transection;
	}
	/**
	 * @param transection the transection to set
	 */
	public void setTransection(UserWalletBo transection) {
		this.transection = transection;
	}
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the cardNo
	 */
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WalletTxBo [id=" + id + ", username=" + username + ", amount=" + amount + ", date=" + date
				+ ", transection=" + transection + "]";
	}
	
	
	
	
	

}
