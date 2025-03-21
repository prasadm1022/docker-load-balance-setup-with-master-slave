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

package org.upwork.prototype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configurations
 *
 * @author prasadm
 * @since 14 June 2022
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
