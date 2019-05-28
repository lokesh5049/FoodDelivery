package com.sapphireims.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapphireims.bo.RoleBo;
import com.sapphireims.bo.UsersBo;
import com.sapphireims.dao.UserDao;
import com.sapphireims.dao.UserDaoImpl;
import com.sapphireims.dto.UsersDto;
/**
 * For login using roles service
 * 
 * @author lokesh.yadav
 * @since   2019-01-14
 *
 */

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService  {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao dao;
	

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) {
	
	 UsersBo users;
	try {
		users = dao.checkCred(username);
	
		List<GrantedAuthority> authorities = 
                                      buildUserAuthority(users.getRole());
		return buildUserForAuthentication(users, authorities);
	} 
		catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}

		return null;
		
	}

	
	private User buildUserForAuthentication(UsersBo user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), user.getPassword(), 
			user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<RoleBo> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<>();
		for (RoleBo userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getUserrole()));
		}
		return new ArrayList<>(setAuths);
	}
	  
}
