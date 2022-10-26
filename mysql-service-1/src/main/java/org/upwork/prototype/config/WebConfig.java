package org.upwork.prototype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configurations
 *
 * @author prasadm
 * @since 29 May 2022
 */

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer
{
    private static final long MAX_AGE_SECS = ( 60 * 60 );

    @Override
    public void addCorsMappings( CorsRegistry registry )
    {
        registry.addMapping( "/**" )
                .allowedOrigins( "*" )
                .allowedMethods( "GET", "OPTIONS", "POST", "PUT", "PATCH", "DELETE" )
                .allowedHeaders( "*" )
                .allowCredentials( false )
                .maxAge( MAX_AGE_SECS );
    }
}
