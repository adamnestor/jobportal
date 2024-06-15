package com.luv2code.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.luv2code.jobportal.services.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	public WebSecurityConfig(CustomUserDetailsService customUserDetailsService,
			CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	}

	private final String[] publicUrl = { "/", "/global-search/**", "/register", "/register/**", "/webjars/**",
			"/resources/**", "/assets/**", "/css/**", "/summernote/**", "/js/**", "/*.css", "/*.js", "/*.js.map",
			"/fonts**", "/favicon.ico", "/resources/**", "/error" };

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(authenticationProvider());

		// Allows the above to not require authentication such as registering or css js
		// files
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(publicUrl).permitAll();
			auth.anyRequest().authenticated();
		});

		http.formLogin(form -> form.loginPage("/login").permitAll().successHandler(customAuthenticationSuccessHandler))
				.logout(logout -> {
					logout.logoutUrl("/logout");
					logout.logoutSuccessUrl("/");
				}).cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable());

		return http.build();
	}

	// Tell Spring Security how to find our users and hot to authenticate passwords
	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(customUserDetailsService); // Tell SS how to retrieve the users
																				// from the database
		return authenticationProvider;
	}

	// Tell Spring Security how to authenticate passwords (plain text or encryption)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
