package com.sapphireims.bo;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Users Business Object for persistent use
 * Simple POJO class
 * @author lokesh.yadav
 * @since   2019-01-09 
 *
 */
@Entity
@Table(name = "users")
public class UsersBo implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Id
	@Column(name = "email", unique = true, nullable = false, length = 45)
	private String email;
	@Column(name = "phone")
	private long phoneNo;
	@Column
	private String address;
	@Column
	private String password;
	@Column(nullable = false, columnDefinition = "BIT default 1", length = 1)
	private boolean enabled=true;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "username", targetEntity = RoleBo.class)
	@Fetch(FetchMode.JOIN)
	private Set<RoleBo> role = new HashSet<>(0);
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	@Fetch(FetchMode.JOIN)
	private Set<ProductBo> product = new HashSet<>(0);	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userid", targetEntity = UserWalletBo.class)
	@Fetch(FetchMode.JOIN)
	private Set<UserWalletBo> wallet = new HashSet<>(0);

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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNo
	 */
	public long getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the role
	 */

	public Set<RoleBo> getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */

	public void setRole(Set<RoleBo> role) {
		this.role = role;
	}

	/**
	 * @return the product
	 */
	public Set<ProductBo> getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Set<ProductBo> product) {
		this.product = product;
	}

	/**
	 * @return the wallet
	 */
	public Set<UserWalletBo> getWallet() {
		return wallet;
	}

	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(Set<UserWalletBo> wallet) {
		this.wallet = wallet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsersBo [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", product=" + product + ", wallet=" + wallet + "]";
	}

}
