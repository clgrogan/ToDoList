package com.todo.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
}
