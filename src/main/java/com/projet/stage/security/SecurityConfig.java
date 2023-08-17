package com.projet.stage.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projet.stage.services.UserService;
import com.projet.stage.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userimpl;
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userimpl).passwordEncoder(passwordEncoder());
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
                .antMatchers("/api/v1/user/save","/api/v1/user/login").permitAll() // Allow registration endpoint without authentication
                .antMatchers("/api/**").authenticated() // Secure other endpoints
                .anyRequest().permitAll() // Allow all other requests
                .and()
            .httpBasic()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// Use basic authentication

        // Add custom authentication provider if needed
        // http.authenticationProvider(customAuthenticationProvider);
    }
}
