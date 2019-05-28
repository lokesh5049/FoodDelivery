package com.sapphireims.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
/**
 * Spring Security Configuration java file
 * 
 * 
 * @author lokesh.yadav
 * @since   2019-01-16 
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.sessionManagement().invalidSessionUrl("/login")
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(2).expiredUrl("/login")
       ;
		http.csrf().disable().authorizeRequests()
        .antMatchers("/register/**")
        .permitAll().and().authorizeRequests()
        .antMatchers("/food/**")
        .permitAll().and().authorizeRequests()
        .antMatchers("/home/**")
        .permitAll()   
        .and().authorizeRequests().antMatchers("/admin/**")
			.hasRole("admin").antMatchers("/customer/**")
			.hasRole("customer").antMatchers("/ewalllet/**")
			.hasRole("customer")
			.antMatchers("/provider/**")
			.access("hasRole('provider')").and().formLogin()
			.loginPage("/login").permitAll().defaultSuccessUrl("/au2").
			failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID")
				.and().exceptionHandling().accessDeniedPage("/unauthrized");
	
	}
	
	 @Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 }
		
   
}
