package com.stock.sweet.sweetstockapi.security;

import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/companies").permitAll()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .antMatchers(HttpMethod.POST, "/product-images").permitAll()
//                .antMatchers(HttpMethod.POST, "/employees").permitAll()
//                .antMatchers(HttpMethod.GET, "/employees").hasAuthority("ADMINISTRATOR")
//                .antMatchers(HttpMethod.GET, "/products").hasAuthority("ADMINISTRATOR")
//                .antMatchers(HttpMethod.PUT, "/products").hasAuthority("ADMINISTRATOR")
//                .antMatchers("/dashboards").hasAnyAuthority("ADMINISTRATOR", "EMPLOYEE")
//                .antMatchers("/provider/**").hasAuthority("ADMINISTRATOR")
//                .antMatchers("/accesses/invite").hasAuthority("ADMINISTRATOR")
//                .antMatchers(HttpMethod.POST, "/ingredients").permitAll()
//                .antMatchers(HttpMethod.GET, "/ingredients/expired").permitAll()
//                .antMatchers(HttpMethod.POST, "/ingredients-reports/create").permitAll()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), companyService))
//                .addFilter(new JwtValidationFilter(authenticationManager()))
//                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/accounts").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), companyService))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/**.yml");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
