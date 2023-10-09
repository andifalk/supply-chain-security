package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    @Order(1)
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        httpSecurity
                .securityMatchers((matchers) -> matchers.requestMatchers(new MvcRequestMatcher(introspector, "/api/**")))
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(withDefaults())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .authorizeHttpRequests(
                    request ->
                        request.anyRequest().authenticated()
                );
        return httpSecurity.build();
    }

    @Bean
    public SecurityFilterChain publicAccess(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        httpSecurity
                .securityMatchers((matchers) -> matchers
                .requestMatchers(new MvcRequestMatcher(introspector,"/**"))
        ).authorizeHttpRequests(
            request -> request.anyRequest().permitAll()
        ).csrf(AbstractHttpConfigurer::disable).headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
         .formLogin(withDefaults())
         .httpBasic(withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("secret").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER", "ADMIN").build());
        return manager;
    }
}
