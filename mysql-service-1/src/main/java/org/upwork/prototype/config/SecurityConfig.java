package org.upwork.prototype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.upwork.prototype.authentication.JwtAuthenticationFilter;

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
    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http.cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

        http.authorizeRequests().antMatchers( HttpMethod.GET, "/**" ).authenticated();
        http.authorizeRequests().antMatchers( HttpMethod.OPTIONS, "/**" ).authenticated();
        http.authorizeRequests().antMatchers( HttpMethod.POST, "/**" ).authenticated();
        http.authorizeRequests().antMatchers( HttpMethod.PUT, "/**" ).authenticated();
        http.authorizeRequests().antMatchers( HttpMethod.PATCH, "/**" ).authenticated();
        http.authorizeRequests().antMatchers( HttpMethod.DELETE, "/**" ).authenticated();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore( authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class );
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
    public HttpSessionEventPublisher httpSessionEventPublisher()
    {
        return new HttpSessionEventPublisher();
    }
}
