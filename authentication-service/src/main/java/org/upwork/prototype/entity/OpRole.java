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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Role Entity
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Entity
@Table( name = "OP_ROLE" )
public class OpRole implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_role" )
    @SequenceGenerator( name = "id_seq_role", sequenceName = "ROLE_ID_SEQ", allocationSize = 1 )
    @Column( name = "ID", unique = true )
    private Long id;

    @Column( name = "NAME", unique = true )
    @Size( max = 128 )
    private String name;

    @OneToMany( mappedBy = "opRole", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER )
    private List<OpUserRole> opUserRoles = new ArrayList<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        OpRole role = ( OpRole ) o;
        return Objects.equals( id, role.id )
                       && Objects.equals( name, role.name )
                       && Objects.equals( opUserRoles, role.opUserRoles );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, opUserRoles );
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public List<OpUserRole> getOpUserRoles()
    {
        return opUserRoles;
    }

    public void setOpUserRoles( List<OpUserRole> opUserRoles )
    {
        this.opUserRoles = opUserRoles;
    }
}
