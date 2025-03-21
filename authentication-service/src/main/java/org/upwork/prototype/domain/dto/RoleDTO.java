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

package org.upwork.prototype.domain.dto;

import java.util.Objects;

/**
 * Role DTO
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class RoleDTO
{
    private Long id;
    private String name;

    public RoleDTO()
    {
    }

    public RoleDTO( Long id, String name )
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        RoleDTO roleDTO = ( RoleDTO ) o;
        return id == roleDTO.id
                       && Objects.equals( name, roleDTO.name );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name );
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
}
