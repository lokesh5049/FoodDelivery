/** 
 * Java class
 * 
 */
package com.sapphireims.dto;

/**
 * @author lokesh.yadav
 *
 * @since Jan 25, 2019
 */
public class WalletTxDto {
	
	
	private int id;
	
	private String userid;
	
	private double amount;
	
	private String date;
	
	private long cardNo;
	
	private String type;
	

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
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WalletTxDto [id=" + id + ", userid=" + userid + ", amount=" + amount + ", date=" + date + ", cardNo="
				+ cardNo + ", added=" + type +  "]";
	}
	

}
