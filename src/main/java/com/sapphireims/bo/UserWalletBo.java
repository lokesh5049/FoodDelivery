package com.sapphireims.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author lokesh.yadav
 *
 * @since Jan 21, 2019
 */
@Entity
@Table(name="wallet")
public class UserWalletBo  implements Serializable{
	
	private static final long serialVersionUID = -8312957565654541361L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false,unique=true)
	private int id;
	@Column(name="wallet_money")
	private double amount;
	@Column
	private Date date;
	@OneToOne(fetch = FetchType.LAZY ,targetEntity=UsersBo.class,cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "userid")
	private UsersBo userid;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transection", targetEntity = WalletTxBo.class ,cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<WalletTxBo> tx = new HashSet<WalletTxBo>(0);
	
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
	 * @return the userid
	 */
	public UsersBo getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(UsersBo userid) {
		this.userid = userid;
	}
	/**
	 * @return the tx
	 */
	public Set<WalletTxBo> getTx() {
		return tx;
	}
	/**
	 * @param tx the tx to set
	 */
	public void setTx(Set<WalletTxBo> tx) {
		this.tx = tx;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserWalletBo [id=" + id + ", amount=" + amount + ", date=" + date + ", userid=" + userid + ", tx=" + tx
				+ "]";
	}
	
	
	

}
