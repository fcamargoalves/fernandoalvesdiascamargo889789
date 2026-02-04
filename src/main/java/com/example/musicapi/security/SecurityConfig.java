package com.example.musicapi.security;

import com.example.musicapi.config.RateLimitFilter;
import org.springframework.context.annotation.Bean;

@Bean
public SecurityFilterChain filterChain(
        HttpSecurity http,
        JwtFilter jwtFilter,
        RateLimitFilter rateLimitFilter
) throws Exception {

    http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**", "/actuator/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(rateLimitFilter, JwtFilter.class);

    return http.build();
}

