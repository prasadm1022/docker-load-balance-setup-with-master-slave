package org.upwork.prototype.domain.dto;

import java.util.Objects;

public class TokenBodyDTO
{
    private String token;

    public TokenBodyDTO()
    {
    }

    public TokenBodyDTO( String token )
    {
        this.token = token;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        TokenBodyDTO that = ( TokenBodyDTO ) o;
        return Objects.equals( token, that.token );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( token );
    }

    public String getToken()
    {
        return token;
    }

    public void setToken( String token )
    {
        this.token = token;
    }
}
