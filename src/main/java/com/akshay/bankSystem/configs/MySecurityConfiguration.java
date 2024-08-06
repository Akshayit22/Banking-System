package com.akshay.bankSystem.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.akshay.bankSystem.security.CustomUserDetailService;
import com.akshay.bankSystem.security.JwtAuthenticationEntryPoint;
import com.akshay.bankSystem.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	private static final String[] WHITE_LIST_URL = { "/api/auth/**", "/error", "/health-check", "/api-docs/**",
			"/swagger-ui/**", "/swagger-resources/*", "/sample/transaction" };

	private static final String[] ADMIN_LIST_URL = { "/admin/**", "/bank/** " };

	private static final String[] EMPLOYEE_LIST_URL = { "/employee/**" };

	private static final String[] EMPLOYEE_ADMIN_LIST_URL = { "/bank/** ", "/bank/**" };

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	CorsConfigurationSource corsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
		corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
		corsConfiguration.setAllowedHeaders(List.of("*"));
		corsConfiguration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			
		http.csrf(csrf -> csrf.disable())
				.cors(httpSecurityCorsConigurer->httpSecurityCorsConigurer.configurationSource(corsConfig()))
				.authorizeHttpRequests(requests -> requests.requestMatchers(WHITE_LIST_URL)
				.permitAll().requestMatchers(EMPLOYEE_ADMIN_LIST_URL)
				.hasAnyAuthority(Constants.ADMIN_USER, Constants.EMPLOYEE_USER).requestMatchers(EMPLOYEE_LIST_URL)
				.hasAnyAuthority(Constants.EMPLOYEE_USER).requestMatchers(ADMIN_LIST_URL)
				.hasAnyAuthority(Constants.ADMIN_USER).anyRequest().authenticated())
				.exceptionHandling(handling -> handling.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}
}