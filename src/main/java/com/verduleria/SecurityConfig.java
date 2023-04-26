package com.verduleria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
    /*
    //Se definen los usuarios del sistema en formato de memoria
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("santi")
                .password("{noop}3110")
                .roles("USER", "VENDEDOR", "ADMIN")
                .build();
        UserDetails vendedor = User.builder()
                .username("diego")
                .password("{noop}1719")
                .roles("USER", "VENDEDOR")
                .build();
        UserDetails usuario = User.builder()
                .username("johel")
                .password("{noop}6969")
                .roles("USER" )
                .build();
        return new InMemoryUserDetailsManager(usuario, vendedor, admin);
    }*/
    
    /*
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
        
        return http.build();*/
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/",
                        "/index",
                        "/errores/**",
                        "/carrito/**",
                        "/webjars/**").permitAll()
                .requestMatchers(
                        "/producto/nuevo",
                        "/producto/guardar",
                        "/producto/modificar/**",
                        "/producto/eliminar/**",
                        "/categoria/nuevo",
                        "/categoria/guardar",
                        "/categoria/modificar/**",
                        "/categoria/eliminar/**",
                        "/cliente/nuevo",
                        "/cliente/guardar",
                        "/cliente/modificar/**",
                        "/cliente/eliminar/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/cliente/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll())
                .exceptionHandling()
                .accessDeniedPage("/errores/403");
        return http.build();
    }
    } 
