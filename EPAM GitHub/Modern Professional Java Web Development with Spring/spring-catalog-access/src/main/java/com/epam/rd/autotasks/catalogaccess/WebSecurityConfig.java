package com.epam.rd.autotasks.catalogaccess;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MANAGER = "MANAGER";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String CUSTOMER = "CUSTOMER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String employeesEndPoints = "/employees/**";
        String catalogEndPoints = "/catalog/**";
        String salariesEndPoints = "/salaries/**";
        String salariesEndPoint = "/salaries";
        String login = "/login";

        http
                .csrf().disable()
                .authorizeRequests()
                //employees
                .antMatchers(HttpMethod.GET, employeesEndPoints).hasAnyRole(MANAGER, EMPLOYEE)
                .antMatchers(HttpMethod.POST, employeesEndPoints).hasRole(MANAGER)
                //salary
                .antMatchers(salariesEndPoint).hasRole(MANAGER)
                .antMatchers(salariesEndPoints).hasAnyRole(MANAGER, EMPLOYEE)
                //catalog
                .antMatchers(catalogEndPoints).hasAnyRole(MANAGER, EMPLOYEE, CUSTOMER)
                    .and()
                    .formLogin()
                    .loginPage(login)
                    .permitAll()
                        .and()
                        .logout()
                        .permitAll();

    }
}