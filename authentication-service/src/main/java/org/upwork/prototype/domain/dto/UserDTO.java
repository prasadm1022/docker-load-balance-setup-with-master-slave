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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User DTO
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class UserDTO
{
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private List<String> roles = new ArrayList<>();

    public UserDTO()
    {
    }

    public UserDTO( Long id, String name, String username, String email, String password, List<String> roles )
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        UserDTO userDTO = ( UserDTO ) o;
        return Objects.equals( id, userDTO.id )
                       && Objects.equals( name, userDTO.name )
                       && Objects.equals( username, userDTO.username )
                       && Objects.equals( email, userDTO.email )
                       && Objects.equals( password, userDTO.password )
                       && Objects.equals( roles, userDTO.roles );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, username, email, password, roles );
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles( List<String> roles )
    {
        this.roles = roles;
    }
}
