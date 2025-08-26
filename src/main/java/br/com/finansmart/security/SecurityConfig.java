package br.com.finansmart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indica que esta é uma classe de configuração do Spring
@EnableWebSecurity // Ativa a segurança web do Spring
public class SecurityConfig {

    @Bean // Indica que este método produz um "Bean" a ser gerido pelo Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF, pois não usamos sessões
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a gestão de sessão como sem estado (stateless)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll() // PERMITE todos os pedidos POST para /api/users
                        .anyRequest().authenticated() // EXIGE autenticação para qualquer outro pedido
                )
                .build();
    }
}