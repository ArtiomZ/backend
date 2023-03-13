package ru.netology.backendservice.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUsrDetailsService customUsrDetailsService;

    public SecurityConfiguration(CustomUsrDetailsService customUsrDetailsService) {
        this.customUsrDetailsService = customUsrDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST,"/api").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(customUsrDetailsService)
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



