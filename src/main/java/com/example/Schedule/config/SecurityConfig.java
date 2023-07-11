package com.example.Schedule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth->{
//                    auth.requestMatchers("/").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                        .oauth2Login(Customizer.withDefaults())
//                                .formLogin(Customizer.withDefaults());
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/registration").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("").permitAll()
                                .requestMatchers("redirect:/").permitAll()
                                .anyRequest().authenticated());
        http
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll());
        http
                .logout((logout) -> logout
                        .permitAll());
        return http.build();
    }

//    private static List<String> clients = Arrays.asList("google", "facebook");



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


}
