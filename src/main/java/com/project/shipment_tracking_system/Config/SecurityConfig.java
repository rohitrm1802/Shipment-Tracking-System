package com.project.shipment_tracking_system.Config;

import com.project.shipment_tracking_system.Security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/user/register","/user/login","/test",
                                "/ws/**",
                        "/api/tracking/location")
                        .permitAll()
                        .requestMatchers("/shipment/create").hasRole("SHIPPER")
                        .requestMatchers("/shipment/getAllShipment").hasRole("SHIPPER")
                        .requestMatchers("/shipment/getShipmentById/{shipmentId}").hasRole("SHIPPER")
                        .requestMatchers("/shipment/updateShipment/{shipmentId}").hasRole("SHIPPER")
                        .requestMatchers("/bid/placeBid/{shipmentId}").hasRole("CARRIER")
                        .requestMatchers("/bid/getBids/{shipmentId}").hasRole("SHIPPER")
                        .requestMatchers("/bid/getBidById/{bidId}").hasAnyRole("CARRIER","SHIPPER")
                        .requestMatchers("/bid/acceptBid/{shipmentId}/{bidId}").hasRole("SHIPPER")
                        .requestMatchers("/bid/rejectBid/{shipmentId}/{bidId}").hasRole("SHIPPER")
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> httpBasic.disable())
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
    {
        return config.getAuthenticationManager();
    }
}

