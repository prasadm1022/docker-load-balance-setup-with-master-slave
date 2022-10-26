package org.upwork.prototype.mapper;

import org.springframework.stereotype.Component;
import org.upwork.prototype.domain.dto.UserDTO;
import org.upwork.prototype.entity.OpUser;
import org.upwork.prototype.entity.OpUserRole;

/**
 * User Mapper
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Component
public class UserMapper
{
    public UserDTO toDto( OpUser opUser )
    {
        UserDTO user = new UserDTO();

        user.setId( opUser.getId() );
        user.setName( opUser.getName() );
        user.setUsername( opUser.getUsername() );
        user.setEmail( opUser.getEmail() );
        user.setPassword( opUser.getPassword() );
        for( OpUserRole opUserRole : opUser.getOpUserRoles() )
        {
            user.getRoles().add( opUserRole.getOpRole().getName() );
        }

        return user;
    }

    public OpUser toEntity( UserDTO user )
    {
        OpUser opUser = new OpUser();

        opUser.setId( user.getId() );
        opUser.setName( user.getName() );
        opUser.setUsername( user.getUsername() );
        opUser.setEmail( user.getEmail() );
        opUser.setPassword( user.getPassword() );

        return opUser;
    }
}
