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

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * User Principal
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class UserPrincipalDTO
{
    private Long id;
    private String name;
    private String username;
    private String email;
    private String token;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

    public UserPrincipalDTO()
    {
    }

    public UserPrincipalDTO( Long id, String name, String username, String email, String token, Collection<? extends GrantedAuthority> authorities )
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.token = token;
        this.authorities = authorities;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        UserPrincipalDTO that = ( UserPrincipalDTO ) o;
        return Objects.equals( id, that.id )
                       && Objects.equals( name, that.name )
                       && Objects.equals( username, that.username )
                       && Objects.equals( email, that.email )
                       && Objects.equals( token, that.token )
                       && Objects.equals( authorities, that.authorities );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, username, email, token, authorities );
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

    public String getToken()
    {
        return token;
    }

    public void setToken( String token )
    {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities( Collection<? extends GrantedAuthority> authorities )
    {
        this.authorities = authorities;
    }
}
