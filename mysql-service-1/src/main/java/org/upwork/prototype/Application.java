package org.upwork.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Application Main Class
 *
 * @author prasadm
 * @since 29 May 2022
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
    {
        return application.sources( Application.class );
    }

    @Bean
    public RestTemplate restTemplate( RestTemplateBuilder restTemplateBuilder )
    {
        return restTemplateBuilder.build();
    }
}
