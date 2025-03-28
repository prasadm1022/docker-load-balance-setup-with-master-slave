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

package org.upwork.prototype.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT Authentication Filter
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private static final Logger LOGGER = LoggerFactory.getLogger( JwtAuthenticationFilter.class );
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain ) throws ServletException, IOException
    {
        try
        {
            String jwt = getJwtFromRequest( httpServletRequest );
            if( StringUtils.hasText( jwt ) && jwtTokenProvider.validateToken( jwt ) )
            {
                UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication( jwt );
                authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails( httpServletRequest ) );
                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
        }
        catch( Exception ex )
        {
            LOGGER.error( "Could not set authentication in security context by jwt", ex );
        }
        filterChain.doFilter( httpServletRequest, httpServletResponse );
    }

    private String getJwtFromRequest( HttpServletRequest request )
    {
        String bearerToken = request.getHeader( AUTHORIZATION_HEADER_KEY );
        if( StringUtils.hasText( bearerToken ) && bearerToken.startsWith( AUTH_HEADER_PREFIX ) )
        {
            return bearerToken.substring( AUTH_HEADER_PREFIX.length() );
        }
        return null;
    }
}
