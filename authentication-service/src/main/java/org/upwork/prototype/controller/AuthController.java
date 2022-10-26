package org.upwork.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.upwork.prototype.authentication.JwtResponse;
import org.upwork.prototype.authentication.JwtTokenProvider;
import org.upwork.prototype.domain.dto.TokenBodyDTO;
import org.upwork.prototype.domain.dto.UserDTO;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseWrapper;
import org.upwork.prototype.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Auth REST Controller
 *
 * @author prasadm
 * @since 14 June 2022
 */

@RestController
public class AuthController implements IAuthController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<ResponseWrapper<JwtResponse>> login( HttpServletRequest request, UserDTO user )
    {
        try
        {
            Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword() ) );
            SecurityContextHolder.getContext().setAuthentication( authentication );
            JwtResponse accessToken = jwtTokenProvider.generateToken( user );
            return ResponseUtil.wrap( new Response<>( accessToken ) );
        }
        catch( Exception ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<Authentication>> validate( TokenBodyDTO token )
    {
        try
        {
            boolean isValid = jwtTokenProvider.validateToken( token.getToken() );
            if( isValid )
            {
                UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication( token.getToken() );
                return ResponseUtil.wrap( new Response<>( authentication ) );
            }
            else
            {
                throw new Exception( "Invalid JWT" );
            }
        }
        catch( Exception ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
