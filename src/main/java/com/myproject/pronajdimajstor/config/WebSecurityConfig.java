package com.myproject.pronajdimajstor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;


    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/", "/home", "/assets/**", "/register/client", "/register/majstor", "/register", "/category", "/subCategory/**", "/majstori/**", "/aboutUs", "/contact", "/profile/**").permitAll()
                .antMatchers("/admin/**", "/addCategory", "/editCategory/**", "/createCategory/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {



        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider);

    }

}
