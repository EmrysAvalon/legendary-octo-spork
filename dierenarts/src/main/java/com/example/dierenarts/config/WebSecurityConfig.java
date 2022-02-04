package com.example.dierenarts.config;

import com.example.dierenarts.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String RECEPTIONIST = "RECEPTIONIST";
    public static final String ADMINISTRATIVE_WORKER = "ADMINISTRATIVE_WORKER";
    public static final String ADMIN = "ADMIN";

    @Autowired
    private DataSource dataSource;

    @Autowired
    WebSecurityConfig(DataSource dataSource, JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(PATCH,"/users/{^[\\w]$}/password").authenticated()
                .antMatchers(GET,"/users/**").hasRole(ADMIN)
                .antMatchers(DELETE, "/users/**").hasRole(ADMIN)
                .antMatchers(GET, "/files/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(POST, "/upload/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(GET, "/owners/**/pets").hasRole(RECEPTIONIST)
                .antMatchers(PATCH, "/pets/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(POST, "/pets/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(DELETE, "/pets/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(GET, "/pets/**").hasRole(RECEPTIONIST)
                .antMatchers(PATCH, "/owners/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(POST, "/owners/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(DELETE, "/owners/**").hasRole(ADMINISTRATIVE_WORKER)
                .antMatchers(GET, "/owners/**").hasRole(ADMINISTRATIVE_WORKER)

                .anyRequest().permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}