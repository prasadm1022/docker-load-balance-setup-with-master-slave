package org.upwork.prototype.authentication;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.upwork.prototype.domain.dto.UserDTO;
import org.upwork.prototype.domain.dto.UserPrincipalDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JWT Token Provider
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Component
public class JwtTokenProvider implements Serializable
{
    private static final Logger LOGGER = LoggerFactory.getLogger( JwtTokenProvider.class );
    private static final String AUTHORITIES_CLAIM_KEY = "roles";
    private static final String USER_ID_CLAIM_KEY = "id";
    private static final String NAME_CLAIM_KEY = "name";
    private static final String USER_NAME_CLAIM_KEY = "username";
    private static final String EMAIL_CLAIM_KEY = "email";

    @Value( "${upwork.prototype.jwtSecret}" )
    private String jwtSecret;

    @Value( "${upwork.prototype.jwtExpiration}" )
    private long jwtExpiration;

    @Value( "${upwork.prototype.refreshJwtExpiration}" )
    private long refreshJwtExpiration;

    /**
     * generate JWT token
     *
     * @param user user dto
     * @return JwtResponse
     */
    public JwtResponse generateToken( UserDTO user )
    {
        // generate JWT token
        Claims tokenClaims = Jwts.claims().setSubject( String.valueOf( user.getId() ) );
        tokenClaims.put( USER_ID_CLAIM_KEY, user.getId() );
        tokenClaims.put( NAME_CLAIM_KEY, user.getName() );
        tokenClaims.put( USER_NAME_CLAIM_KEY, user.getUsername() );
        tokenClaims.put( EMAIL_CLAIM_KEY, user.getEmail() );
        tokenClaims.put( AUTHORITIES_CLAIM_KEY, user.getRoles() );
        Date tokenExpireDate = new Date( System.currentTimeMillis() + jwtExpiration );
        String token = Jwts.builder().addClaims( tokenClaims ).signWith( SignatureAlgorithm.HS256, jwtSecret ).setIssuedAt( new Date( System.currentTimeMillis() ) ).setExpiration( tokenExpireDate ).compact();

        // generate JWT refresh token
        Claims refreshTokenClaims = Jwts.claims().setSubject( String.valueOf( user.getId() ) );
        refreshTokenClaims.put( USER_ID_CLAIM_KEY, user.getId() );
        refreshTokenClaims.put( NAME_CLAIM_KEY, user.getName() );
        refreshTokenClaims.put( USER_NAME_CLAIM_KEY, user.getUsername() );
        refreshTokenClaims.put( EMAIL_CLAIM_KEY, user.getEmail() );
        refreshTokenClaims.put( AUTHORITIES_CLAIM_KEY, user.getRoles() );
        Date refreshTokenExpireDate = new Date( System.currentTimeMillis() + refreshJwtExpiration );
        String refreshToken = Jwts.builder().addClaims( refreshTokenClaims ).signWith( SignatureAlgorithm.HS256, jwtSecret ).setIssuedAt( new Date( System.currentTimeMillis() ) ).setExpiration( refreshTokenExpireDate ).compact();

        return new JwtResponse( token, tokenExpireDate.getTime(), refreshToken, refreshTokenExpireDate.getTime() );
    }

    /**
     * validate JWT token
     *
     * @param authToken authToken
     * @return boolean
     */
    public boolean validateToken( String authToken )
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
}
