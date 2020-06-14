package com.pukhalskyi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER_URL = "/*/api/users/**";
    private static final String TICKET_URL = "/*/api/tickets/**";
    private static final String ROLE_ADMIN = "ADMIN";
//    private static final String ROLE_CASHIER = "CASHIER";

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT nickname, password, true FROM customer WHERE nickname=?")
                .authoritiesByUsernameQuery("SELECT nickname, role FROM customer WHERE nickname=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/*/api/users/{username}").permitAll()
                .antMatchers(HttpMethod.GET, USER_URL).hasAnyRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, USER_URL).permitAll()
                .antMatchers(HttpMethod.DELETE, USER_URL).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, TICKET_URL).permitAll()
                .antMatchers(HttpMethod.POST, TICKET_URL).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, TICKET_URL).hasRole(ROLE_ADMIN)
                .and()
                .csrf().disable()
                .formLogin().disable();


        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SimpleUrlAuthenticationFailureHandler FailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
