package com.tiendatech.config;

import com.tiendatech.service.ClienteUserDetailsService;
import com.tiendatech.service.CustomAdminDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    SecurityFilterChain adminChain(HttpSecurity http,
            CustomAdminDetailsService adminDetails) throws Exception {
        DaoAuthenticationProvider adminProvider = new DaoAuthenticationProvider();
        adminProvider.setUserDetailsService(adminDetails);
        adminProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        http
                .securityMatcher("/admin/**")
                .authenticationProvider(adminProvider)
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/admin/login_admin", "/admin/login",
                        "/admin/css/**", "/admin/js/**", "/admin/img/**"
                ).permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/admin/login_admin")
                .loginProcessingUrl("/admin/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                );

        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain clienteChain(HttpSecurity http,
            ClienteUserDetailsService clienteDetails) throws Exception {
        DaoAuthenticationProvider clienteProvider = new DaoAuthenticationProvider();
        clienteProvider.setUserDetailsService(clienteDetails);
        clienteProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        http
                .authenticationProvider(clienteProvider)
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", "/cliente/login_cliente", "/cliente/login_cliente", "/registro",
                        "/categorias", "/ofertas", "/productos/**",
                        "/faq", "/acerca", "/contacto",
                        "/css/**", "/js/**", "/img/**"
                ).permitAll()
                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                .loginPage("/cliente/login_cliente")
                .loginProcessingUrl("/cliente/login_cliente")
                .usernameParameter("email")
                .passwordParameter("clave")
                .defaultSuccessUrl("/", false)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
