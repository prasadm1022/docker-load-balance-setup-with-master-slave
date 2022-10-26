package org.upwork.prototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.upwork.prototype.domain.dto.RoleDTO;
import org.upwork.prototype.domain.dto.UserDTO;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseError;
import org.upwork.prototype.entity.OpRole;
import org.upwork.prototype.entity.OpUser;
import org.upwork.prototype.entity.OpUserRole;
import org.upwork.prototype.mapper.RoleMapper;
import org.upwork.prototype.mapper.UserMapper;
import org.upwork.prototype.repository.IRoleRepository;
import org.upwork.prototype.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * User Service
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Service
public class UserService implements IUserService, UserDetailsService
{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        try
        {
            OpUser opUser = userRepository.findByUsername( username );
            if( opUser == null )
            {
                throw new UsernameNotFoundException( "User not found..." );
            }

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for( OpUserRole opUserRole : opUser.getOpUserRoles() )
            {
                authorities.add( new SimpleGrantedAuthority( opUserRole.getOpRole().getName() ) );
            }

            return new User( opUser.getUsername(), opUser.getPassword(), authorities );
        }
        catch( Exception ex )
        {
            throw new UsernameNotFoundException( ex.getMessage(), ex );
        }
    }

    @Override
    public Response<UserDTO> searchUser( String username ) throws ResponseError
    {
        try
        {
            List<UserDTO> response = new ArrayList<>();
            OpUser opUser = userRepository.findByUsername( username );
            if( opUser != null )
            {
                response.add( userMapper.toDto( opUser ) );
            }
            return new Response<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<RoleDTO> searchRole( String roleName ) throws ResponseError
    {
        try
        {
            List<RoleDTO> response = new ArrayList<>();
            OpRole opRole = roleRepository.findByName( roleName );
            if( opRole != null )
            {
                response.add( roleMapper.toDto( opRole ) );
            }
            return new Response<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<UserDTO> saveUser( UserDTO user ) throws ResponseError
    {
        try
        {
            OpUser opUser = userMapper.toEntity( user );
            opUser.setPassword( new BCryptPasswordEncoder().encode( opUser.getPassword() ) );

            OpUser saveResponse = userRepository.save( opUser );
            return new Response<>( userMapper.toDto( saveResponse ) );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<RoleDTO> saveRole( RoleDTO role ) throws ResponseError
    {
        try
        {
            OpRole saveResponse = roleRepository.save( roleMapper.toEntity( role ) );
            return new Response<>( roleMapper.toDto( saveResponse ) );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<Void> addRoleToUser( String username, String roleName ) throws ResponseError
    {
        try
        {
            OpUser opUser = userRepository.findByUsername( username );
            OpRole opRole = roleRepository.findByName( roleName );
            if( opUser != null && opRole != null )
            {
                OpUserRole opUserRole = new OpUserRole();
                opUserRole.setOpUser( opUser );
                opUserRole.setOpRole( opRole );
                opUser.getOpUserRoles().add( opUserRole );
                userRepository.save( opUser );
            }
            return new Response<>();
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }
}
