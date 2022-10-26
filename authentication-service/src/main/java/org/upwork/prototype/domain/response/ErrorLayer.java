package org.upwork.prototype.domain.response;

/**
 * @author prasadm
 * @since 14 June 2022
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
