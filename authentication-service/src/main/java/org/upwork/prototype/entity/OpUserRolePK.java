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
