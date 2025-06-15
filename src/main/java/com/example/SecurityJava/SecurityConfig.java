package com.example.SecurityJava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //Copied from class SpringBootWebSecurityConfiguration

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated());
        //Make Session stateless
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("user1")
                            .password("{noop}password1")
                            .roles("USER")
                            .build();

            UserDetails admin = User.withUsername("admin")
                        .password("{noop}password1")
                    .roles("ADMIN")
                    .build();

            /*
            InMemoryUserDetailsManager used to create in memory user db
            JdbcUserDetailsManager used to create user in DB
             */
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();

        return  new InMemoryUserDetailsManager(user1, admin);
    }

}
