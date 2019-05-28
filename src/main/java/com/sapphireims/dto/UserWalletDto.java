/** 
 * Java class
 * 
 */
package com.sapphireims.dto;


/**
 * @author lokesh.yadav
 *
 * @since Jan 24, 2019
 */
public class UserWalletDto {

	private int id;
	private String userid;
	private String date;
	private double amount;
	private String cardNo;
	private String cvvno;
	private String expirydate;
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
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the cvvno
	 */
	public String getCvvno() {
		return cvvno;
	}
	/**
	 * @param cvvno the cvvno to set
	 */
	public void setCvvno(String cvvno) {
		this.cvvno = cvvno;
	}
	/**
	 * @return the expirydate
	 */
	public String getExpirydate() {
		return expirydate;
	}
	/**
	 * @param expirydate the expirydate to set
	 */
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserWalletDto [id=" + id + ", userid=" + userid + ", date=" + date + ", amount=" + amount + ", cardNo="
				+ cardNo + ", cvvno=" + cvvno + ", expirydate=" + expirydate + "]";
	}
	
	
}
