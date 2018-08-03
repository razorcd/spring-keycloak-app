package com.example.keycloak.demo;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
//@KeycloakConfiguration
//public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * Registers the KeycloakAuthenticationProvider with the authentication manager.
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(keycloakAuthenticationProvider());
//    }
//
//    /**
//     * Defines the session authentication strategy.
//     */
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/pets").permitAll()
                .antMatchers( "/webjars/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .defaultSuccessUrl("/pets")
            .and()
                .logout()
                .logoutSuccessUrl("/pets")
            .and()
                .csrf().disable()
            ;
    }
}
