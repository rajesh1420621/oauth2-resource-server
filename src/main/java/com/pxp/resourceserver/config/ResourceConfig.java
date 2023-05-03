package com.pxp.resourceserver.config;

import com.pxp.resourceserver.util.RoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
        httpSecurity
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/test").permitAll()
                                .requestMatchers("/auth").permitAll()
                                .requestMatchers("/private").hasRole("USER")
                                .requestMatchers("/public").hasRole("PUBLIC")
                                .anyRequest()
                                .authenticated()
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
        return httpSecurity.build();
    }
}
