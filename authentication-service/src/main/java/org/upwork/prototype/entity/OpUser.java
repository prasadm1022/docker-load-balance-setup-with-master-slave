package org.upwork.prototype.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User Entity
 *
 * @author prasadm
 * @since 14 June 2022
 */

@Entity
@Table( name = "OP_USER" )
public class OpUser implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_user" )
    @SequenceGenerator( name = "id_seq_user", sequenceName = "USER_ID_SEQ", allocationSize = 1 )
    @Column( name = "ID" )
    private Long id;

    @NotBlank
    @Column( name = "NAME" )
    @Size( max = 128 )
    private String name;

    @NotBlank
    @Column( name = "USERNAME", unique = true )
    @Size( max = 128 )
    private String username;

    @NotBlank
    @Column( name = "EMAIL", unique = true )
    @Size( max = 128 )
    private String email;

    @NotBlank
    @Column( name = "PASSWORD" )
    @Size( max = 1024 )
    private String password;

    @OneToMany( mappedBy = "opUser", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER )
    private List<OpUserRole> opUserRoles = new ArrayList<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        OpUser opUser = ( OpUser ) o;
        return Objects.equals( id, opUser.id )
                       && Objects.equals( name, opUser.name )
                       && Objects.equals( username, opUser.username )
                       && Objects.equals( email, opUser.email )
                       && Objects.equals( password, opUser.password )
                       && Objects.equals( opUserRoles, opUser.opUserRoles );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, username, email, password, opUserRoles );
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

    public List<OpUserRole> getOpUserRoles()
    {
        return opUserRoles;
    }

    public void setOpUserRoles( List<OpUserRole> opUserRoles )
    {
        this.opUserRoles = opUserRoles;
    }
}
