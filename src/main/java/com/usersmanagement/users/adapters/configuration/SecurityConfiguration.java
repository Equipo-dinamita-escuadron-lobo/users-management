package com.usersmanagement.users.adapters.configuration;


import com.usersmanagement.users.adapters.input.rest.filter.JwtService;
import com.usersmanagement.users.adapters.input.rest.filter.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final JwtService jwtService;

    @Autowired
    public SecurityConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpsecurity) throws Exception {
        return httpsecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((httprequest) -> httprequest
                        .requestMatchers("/auth/**")
                        .permitAll()
                        .requestMatchers("/user/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new UserAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(ssmg -> ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .build();

    }

}
