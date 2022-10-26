package org.upwork.prototype.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * User Role Primary Key
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Embeddable
public class OpUserRolePK implements Serializable
{
    @Column( name = "USER_ID" )
    private Long userId;

    @Column( name = "ROLE_ID" )
    private Long roleId;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        OpUserRolePK that = ( OpUserRolePK ) o;
        return Objects.equals( userId, that.userId ) && Objects.equals( roleId, that.roleId );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( userId, roleId );
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId( Long userId )
    {
        this.userId = userId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId( Long roleId )
    {
        this.roleId = roleId;
    }
}
