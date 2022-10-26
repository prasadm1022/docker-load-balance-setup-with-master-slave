package org.upwork.prototype.authentication;

import java.util.Objects;

public class JwtResponse
{
    private String token;
    private Long tokenExpireTime;
    private String refreshToken;
    private Long refreshTokenExpireTime;

    public JwtResponse()
    {
    }

    public JwtResponse( String token, Long tokenExpireTime, String refreshToken, Long refreshTokenExpireTime )
    {
        this.token = token;
        this.tokenExpireTime = tokenExpireTime;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken( String token )
    {
        this.token = token;
    }

    public Long getTokenExpireTime()
    {
        return tokenExpireTime;
    }

    public void setTokenExpireTime( Long tokenExpireTime )
    {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken( String refreshToken )
    {
        this.refreshToken = refreshToken;
    }

    public Long getRefreshTokenExpireTime()
    {
        return refreshTokenExpireTime;
    }

    public void setRefreshTokenExpireTime( Long refreshTokenExpireTime )
    {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        JwtResponse that = ( JwtResponse ) o;
        return Objects.equals( token, that.token )
                       && Objects.equals( tokenExpireTime, that.tokenExpireTime )
                       && Objects.equals( refreshToken, that.refreshToken )
                       && Objects.equals( refreshTokenExpireTime, that.refreshTokenExpireTime );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( token, tokenExpireTime, refreshToken, refreshTokenExpireTime );
    }
}
