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
