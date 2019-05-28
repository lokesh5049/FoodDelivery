/**
 * 
 */
package com.sapphireims.dto;




import com.sapphireims.bo.UsersBo;

/**
 * 
 *  Role Data Transfer Object for Service use
 * Simple POJO class
 * 
 * @author lokesh.yadav
 * @since   2019-01-11
 *
 */
public class RoleDto {

	private int id;
	private UsersBo username;
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

}
