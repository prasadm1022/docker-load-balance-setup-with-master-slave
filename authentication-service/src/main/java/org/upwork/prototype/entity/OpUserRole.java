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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * User Role Entity
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Entity
@Table( name = "OP_USER_ROLE" )
public class OpUserRole implements Serializable
{
    @EmbeddedId
    private OpUserRolePK opUserRolePK = new OpUserRolePK();

    @MapsId( "userId" )
    @ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
    @JoinColumn( name = "USER_ID", referencedColumnName = "ID" )
    private OpUser opUser;

    @MapsId( "roleId" )
    @ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
    @JoinColumn( name = "ROLE_ID", referencedColumnName = "ID" )
    private OpRole opRole;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        OpUserRole that = ( OpUserRole ) o;
        return Objects.equals( opUserRolePK, that.opUserRolePK )
                       && Objects.equals( opUser, that.opUser )
                       && Objects.equals( opRole, that.opRole );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( opUserRolePK, opUser, opRole );
    }

    public OpUserRolePK getOpUserRolePK()
    {
        return opUserRolePK;
    }

    public void setOpUserRolePK( OpUserRolePK opUserRolePK )
    {
        this.opUserRolePK = opUserRolePK;
    }

    public OpUser getOpUser()
    {
        return opUser;
    }

    public void setOpUser( OpUser opUser )
    {
        this.opUser = opUser;
    }

    public OpRole getOpRole()
    {
        return opRole;
    }

    public void setOpRole( OpRole opRole )
    {
        this.opRole = opRole;
    }
}
