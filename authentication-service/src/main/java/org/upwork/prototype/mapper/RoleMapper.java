package org.upwork.prototype.mapper;

import org.springframework.stereotype.Component;
import org.upwork.prototype.domain.dto.RoleDTO;
import org.upwork.prototype.entity.OpRole;

/**
 * Role Mapper
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Component
public class RoleMapper
{
    public RoleDTO toDto( OpRole opRole )
    {
        RoleDTO role = new RoleDTO();

        role.setId( opRole.getId() );
        role.setName( opRole.getName() );

        return role;
    }

    public OpRole toEntity( RoleDTO role )
    {
        OpRole opRole = new OpRole();

        opRole.setId( role.getId() );
        opRole.setName( role.getName() );

        return opRole;
    }
}
