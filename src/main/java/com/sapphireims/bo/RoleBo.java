/**
 * 
 */
package com.sapphireims.bo;

import java.io.Serializable;

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
 * Roles Business Object for persistent use
 * Simple POJO class
 * @author lokesh.yadav
 * @since   2019-01-09
 *
 */
@Entity
@Table(name = "roles",catalog="customer")
public class RoleBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false,unique=true)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY ,targetEntity=UsersBo.class)
	@JoinColumn(name = "username", nullable = false)
	private UsersBo username;
	@Column(name="user_role",unique=true)
	private String userrole;
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
	
	public UsersBo getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(UsersBo username) {
		this.username = username;
	}
	/**
	 * @return the userrole
	 */
	public String getUserrole() {
		return userrole;
	}
	/**
	 * @param userrole the userrole to set
	 */
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleBo [id=" + id + ", username=" + username + ", userrole=" + userrole + "]";
	}
	
}
