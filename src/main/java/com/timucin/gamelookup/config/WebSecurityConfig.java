package com.timucin.gamelookup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.timucin.gamelookup.authentication.AuthenticatedUserService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = AuthenticatedUserService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/home", "/home.html", "/", "/sign_up", "/register_success",
						"/contact", "/about","/css/**").permitAll()
				.antMatchers("/{username}/my_lists/**").access("@userAccessChecker.checkAccess(authentication, #username)")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
