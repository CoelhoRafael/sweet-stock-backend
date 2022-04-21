package com.stock.sweet.sweetstockapi.security;

import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CompanyService companyService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/companies").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/product-images").permitAll()
                .antMatchers(HttpMethod.POST, "/employees").permitAll()
                .antMatchers(HttpMethod.GET, "/employees").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/products").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT, "/products").hasAuthority("ADMINISTRATOR")
                .antMatchers("/dashboards").hasAnyAuthority("ADMINISTRATOR", "EMPLOYEE")
                .antMatchers("/provider/**").hasAuthority("ADMINISTRATOR")
                .antMatchers("/accesses/invite").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/ingredients").permitAll()
                .antMatchers(HttpMethod.GET, "/ingredients/expired").permitAll()
                .antMatchers(HttpMethod.POST, "/ingredients-reports/create").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), companyService))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

    }
}
