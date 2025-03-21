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
