package com.fatfa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import com.fatfa.model.serviceImp.UserLoginService;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private UserLoginService usuarioService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@ConditionalOnProperty(name = "service.security.disabled", havingValue = "true")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {

			http.authorizeRequests().anyRequest().authenticated().and().csrf().disable()// DESHABILITAMOS
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
			.accessDeniedHandler(new OAuth2AccessDeniedHandler());

			return http.build();
	}
		

	@Bean("authenticationManager")
	protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(usuarioService)
				.passwordEncoder(encoder()).and().build();
	}

}
