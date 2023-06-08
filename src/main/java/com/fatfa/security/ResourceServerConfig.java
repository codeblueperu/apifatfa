package com.fatfa.security;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fatfa.exceptions.AppAuthorizationException;

@Configuration
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter{
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new AppAuthorizationException())
				.and()
				.requestMatchers()
				.and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/auth/recoverpassword","/api/storage/**")
				.permitAll()
				.antMatchers("/api/v1/**")
				.authenticated()
				.and()				
				.cors()
				.configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.applyPermitDefaultValues();		
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("Access-Control-Allow-Origin","http://localhost:4200")); //http://midomnio.com ||   "http://192.168.117.71:8081"		
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","PATH")); 
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));				

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
