package org.upwork.prototype.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.upwork.prototype.authentication.JwtAuthenticationFilter;
import org.upwork.prototype.service.UserService;

/**
 * Security Configurations
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userService;

    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http.cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

        http.authorizeRequests().antMatchers( "/v1/auth/**" ).permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore( authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class );
    }

    @Override
    public void configure( AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception
    {
        authenticationManagerBuilder.userDetailsService( userService ).passwordEncoder( getPasswordEncoder() );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean()
    {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
