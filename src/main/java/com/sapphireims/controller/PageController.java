package com.sapphireims.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring page to return view (jsp) controller java file
 * 
 * 
 * @author lokesh.yadav
 * @since 2019-01-11
 *
 */
@RestController
public class PageController {



	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;

	}

	@RequestMapping(value = { "/register/user/" }, method = RequestMethod.GET)
	public ModelAndView registerPageUser() {

		ModelAndView model = new ModelAndView();
		model.setViewName("register_user");
		return model;

	}

	@RequestMapping(value = { "/ewallet/" }, method = RequestMethod.GET)
	public ModelAndView walletPage(SecurityContextHolder auth) {

		ModelAndView model = new ModelAndView();
		model.setViewName("wallet");
		return model;
	}

	@RequestMapping(value = { "/food" }, method = RequestMethod.GET)
	public ModelAndView pageFood() {

		ModelAndView model = new ModelAndView();
		model.setViewName("food");
		return model;

	}

	@RequestMapping(value = { "/register/admin_r/" }, method = RequestMethod.GET)
	public ModelAndView registerPageAdmin(HttpSession session) {
      
		ModelAndView model = new ModelAndView();
		model.setViewName("register_admin");
		return model;

	}

	@RequestMapping(value = { "/register/provider_r/" }, method = RequestMethod.GET)
	public ModelAndView registerPageProvider() {

		ModelAndView model = new ModelAndView();
		model.setViewName("register_provider");
		return model;

	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,HttpSession session) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			session.invalidate();
			model.addObject("msg", "You've been logged out successfully.");
			
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/au2", method = RequestMethod.GET)
	public ModelAndView redirectToPageDefault(SecurityContextHolder auth,HttpSession session) {
		Collection<? extends GrantedAuthority> granted = auth.getContext().getAuthentication().getAuthorities();
		String role;
		// set page default to rules common
		for (int i = 0; i < granted.size(); i++) {
			role = granted.toArray()[i] + "";
			// verify if user contain role to view Dashboard page default
			if (role.equals("admin")) {
				return new ModelAndView("admin");

			} else if (role.equals("provider")) {
				return new ModelAndView("provider");
			} else if (role.equals("customer")) {
				return new ModelAndView("customer");

			}

		}
		return new ModelAndView("login");
	}

	
	//geting dashboard 
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(SecurityContextHolder auth,HttpSession session) {
		Collection<? extends GrantedAuthority> granted = auth.getContext().getAuthentication().getAuthorities();
		String role;
		// set page default to rules common
		for (int i = 0; i < granted.size(); i++) {
			role = granted.toArray()[i] + "";
			// verify if user contain role to view Dashboard page default
			if (role.equals("admin")) {
				return new ModelAndView("dashboard_admin");

			} else if (role.equals("provider")) {
				return new ModelAndView("provider");
			} else if (role.equals("customer")) {
				return new ModelAndView("customer");

			}

		}
		return new ModelAndView("login");
	}
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/unauthrized", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("unauthrized");
		return model;

	}
}
