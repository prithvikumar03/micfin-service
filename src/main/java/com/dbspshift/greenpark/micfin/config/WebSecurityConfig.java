/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbspshift.greenpark.micfin.config;


import com.dbspshift.greenpark.micfin.reactiveservices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author didin
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers("/micfin/api/").permitAll()
                .antMatchers("/micfin/auth/login").permitAll()
                .antMatchers("/micfin/auth/signup").permitAll()
                .antMatchers("/swagger-resources/**","/v2/api-docs","/configuration/ui","/swagger-resources","/configuration/security","/swagger-ui.html","/webjars/**").permitAll()
                .antMatchers("/micfin/api/").permitAll()
                .antMatchers("/micfin/sms/**").permitAll()
                .antMatchers("/micfin/transaction/**").permitAll()


                //.antMatchers("/micfin/api/**").hasAuthority("ADMIN").anyRequest()
                //.authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                //.loginPage("/micfin/auth/login").failureUrl("/micfin/auth/login?error=true")
                //.usernameParameter("email")
                //.passwordParameter("password")


                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/micfin/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
