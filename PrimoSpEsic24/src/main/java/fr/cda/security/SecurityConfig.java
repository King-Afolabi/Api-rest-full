package fr.cda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean // Permettre d'intégrer dans le contexte de spring elle permet de l'intégré depuis le lancement de l'application
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
		
		http.csrf().disable();
		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
		return http.build();
	}

}
