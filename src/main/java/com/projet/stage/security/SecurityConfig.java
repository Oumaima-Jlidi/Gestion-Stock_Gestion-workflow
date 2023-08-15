package com.projet.stage.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors() // Enable CORS configuration
                .and()
            .csrf().disable() // Disable CSRF for simplicity
            .authorizeRequests()
                .antMatchers("/api/v1/user/save").permitAll() // Allow registration endpoint without authentication
                .antMatchers("/api/**").authenticated() // Secure other endpoints
                .anyRequest().permitAll() // Allow all other requests
                .and()
            .httpBasic(); // Use basic authentication

        // Add custom authentication provider if needed
        // http.authenticationProvider(customAuthenticationProvider);
    }
}
