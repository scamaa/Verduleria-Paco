package com.verduleria;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
    //Se definen los usuarios del sistema en formato de memoria
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("Santiago")
                .password("{noop}Santica")
                .roles("USER", "VENDEDOR", "ADMIN")
                .build();
        UserDetails vendedor = User.builder()
                .username("Diego")
                .password("{noop}RS1719")
                .roles("USER", "VENDEDOR")
                .build();
        UserDetails usuario = User.builder()
                .username("Johel")
                .password("{noop}Pirulillo")
                .roles("USER" )
                .build();
        return new InMemoryUserDetailsManager(usuario, vendedor, admin);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/",
                                "/index",
                                "/errores/**",
                                "/webjars/**").permitAll()
                        .requestMatchers("/producto/nuevo",
                                "/producto/guardar",
                                "/producto/modificar/**",
                                "/producto/eliminar/**").hasRole("ADMIN")
                );
        
        return http.build();
    } 
}