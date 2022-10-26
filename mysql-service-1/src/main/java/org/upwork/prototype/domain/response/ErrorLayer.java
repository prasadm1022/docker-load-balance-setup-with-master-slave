package org.upwork.prototype.domain.response;

/**
 * @author prasadm
 * @since 29 May 2022
 */

public enum ErrorLayer
{
    API_LAYER( 0 );

    public final int code;

    ErrorLayer( int code )
    {
        this.code = code;
    }
}
