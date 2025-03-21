/*
 * Copyright 2022 Prasad Madusanka Basnayaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.upwork.prototype;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.upwork.prototype.domain.dto.RoleDTO;
import org.upwork.prototype.domain.dto.UserDTO;
import org.upwork.prototype.service.UserService;

import java.util.ArrayList;

/**
 * Application Main Class
 *
 * @author prasadm
 * @since 14 June 2022
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

    // TODO :: below section is for testing purposes only. remove it & insert real data in production
    @Bean
    CommandLineRunner run( UserService userService )
    {
        return args ->
        {
            try
            {
                final String ADMIN = "ADMIN";
                final String USER = "USER";
                final String USERNAME = "prasadm";
                if( userService.searchRole( ADMIN ).getData().isEmpty() )
                {
                    userService.saveRole( new RoleDTO( null, ADMIN ) );
                }
                if( userService.searchRole( USER ).getData().isEmpty() )
                {
                    userService.saveRole( new RoleDTO( null, USER ) );
                }
                if( userService.searchUser( USERNAME ).getData().isEmpty() )
                {
                    userService.saveUser( new UserDTO( null, "Prasad Madusanka", USERNAME, "prasadm1022@gmail.com", "123456789", new ArrayList<>() ) );
                    userService.addRoleToUser( USERNAME, ADMIN );
                    userService.addRoleToUser( USERNAME, USER );
                }
            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }
        };
    }
}
