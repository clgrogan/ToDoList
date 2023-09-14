package com.todo.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.todo.model.ToDo;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		UserDetails userDets1 = buildNewUser("CurtG", "dummy");
		UserDetails userDets2 = buildNewUser("Bob", "dummy");
		return new InMemoryUserDetailsManager(userDets1, userDets2);
	}

	private UserDetails buildNewUser(String userName, String password) {
		Function<String, String> passwordEncoder = imp -> passwordEncoder().encode(imp);
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(userName).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated());
			http.formLogin(withDefaults());
			
			http.csrf().disable();
			http.headers().disable();
			
		return http.build();
	}
}
