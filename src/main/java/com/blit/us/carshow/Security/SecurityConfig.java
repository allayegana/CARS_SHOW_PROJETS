package com.blit.us.carshow.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.
                httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((c) -> {
                    c.anyRequest().authenticated();
                }).build();

    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("ALLAYE")
                .password(bCryptPasswordEncoder().encode("GANA123"))
                .roles("USER")
                .build();

        UserDetails user1 = User.builder()
                .username("USER")
                .password(bCryptPasswordEncoder().encode("PASSWORD"))
                .roles("ADMI")
                .build();
        return new InMemoryUserDetailsManager(user,user1);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
