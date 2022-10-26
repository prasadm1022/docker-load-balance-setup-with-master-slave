package org.upwork.prototype.authentication;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private static final String AUTHORITIES_CLAIM_KEY = "roles";
    private static final String USER_ID_CLAIM_KEY = "id";
    private static final String NAME_CLAIM_KEY = "name";
    private static final String USER_NAME_CLAIM_KEY = "username";
    private static final String EMAIL_CLAIM_KEY = "email";

    @Value( "${upwork.prototype.jwtSecret}" )
    private String jwtSecret;

    @Override
    protected void doFilterInternal( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain ) throws ServletException, IOException
    {
        try
        {
            String jwt = getJwtFromRequest( httpServletRequest );
            boolean isValid = validateJwt( jwt );
            if( isValid )
            {
                UsernamePasswordAuthenticationToken authentication = getAuthentication( jwt );
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities() );
                usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( httpServletRequest ) );
                SecurityContextHolder.getContext().setAuthentication( usernamePasswordAuthenticationToken );
            }
        }
        catch( Exception ex )
        {
            LOGGER.error( "Could not set authentication in security context by jwt", ex );
        }
        filterChain.doFilter( httpServletRequest, httpServletResponse );
    }

    /**
     * get jwt token from the request
     *
     * @param request request
     * @return jwt
     */
    private String getJwtFromRequest( HttpServletRequest request )
    {
        String bearerToken = request.getHeader( AUTHORIZATION_HEADER_KEY );
        if( StringUtils.hasText( bearerToken ) && bearerToken.startsWith( AUTH_HEADER_PREFIX ) )
        {
            return bearerToken.substring( AUTH_HEADER_PREFIX.length() );
        }
        return null;
    }

    /**
     * validate jwt token that received from the request
     *
     * @param authToken jwt token
     * @return isValid
     */
    private boolean validateJwt( String authToken )
    {
        try
        {
            Claims body = Jwts.parser().setSigningKey( jwtSecret ).parseClaimsJws( authToken ).getBody();
            Date expirationDate = body.getExpiration();
            if( expirationDate != null )
            {
                boolean notExpired = expirationDate.after( new Date() );
                if( notExpired )
                {
                    return true;
                }
                else
                {
                    LOGGER.error( "JWT Token expired" );
                    return false;
                }
            }
            else
            {
                LOGGER.error( "JWT Token expiration date not found" );
                return false;
            }
        }
        catch( SignatureException ex )
        {
            LOGGER.error( "Invalid JWT signature" );
        }
        catch( MalformedJwtException ex )
        {
            LOGGER.error( "Invalid JWT token" );
        }
        catch( ExpiredJwtException ex )
        {
            LOGGER.error( "Expired JWT token" );
        }
        catch( UnsupportedJwtException ex )
        {
            LOGGER.error( "Unsupported JWT token" );
        }
        catch( IllegalArgumentException ex )
        {
            LOGGER.error( "JWT claims string is empty." );
        }
        return false;
    }

    /**
     * get authentication
     *
     * @param authToken authToken
     * @return UsernamePasswordAuthenticationToken
     */
    public UsernamePasswordAuthenticationToken getAuthentication( String authToken )
    {
        Claims claims = Jwts.parser().setSigningKey( jwtSecret ).parseClaimsJws( authToken ).getBody();

        Long userId = ( Long ) claims.get( USER_ID_CLAIM_KEY );
        String name = ( String ) claims.get( NAME_CLAIM_KEY );
        String username = ( String ) claims.get( USER_NAME_CLAIM_KEY );
        String email = ( String ) claims.get( EMAIL_CLAIM_KEY );

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        if( claims.get( AUTHORITIES_CLAIM_KEY ) != null )
        {
            authorities = ( ( List<String> ) claims.get( AUTHORITIES_CLAIM_KEY ) ).stream().map( SimpleGrantedAuthority::new ).collect( Collectors.toList() );
        }

        UserPrincipalDTO userPrincipal = new UserPrincipalDTO( userId, name, username, email, authToken, authorities );

        return new UsernamePasswordAuthenticationToken( userPrincipal, "", authorities );
    }

    private static class UserPrincipalDTO
    {
        private Long id;
        private String name;
        private String username;
        private String email;
        private String token;
        private Collection<? extends GrantedAuthority> authorities;

        public UserPrincipalDTO( Long id, String name, String username, String email, String token, Collection<? extends GrantedAuthority> authorities )
        {
            this.id = id;
            this.name = name;
            this.username = username;
            this.email = email;
            this.token = token;
            this.authorities = authorities;
        }
    }
}
