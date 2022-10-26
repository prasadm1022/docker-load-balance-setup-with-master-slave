package org.upwork.prototype.domain.response;

/**
 * Status
 *
 * @author prasadm
 * @since 29 May 2022
 */

public enum Status
{
    SUCCESS( 1, "Success" ),
    WARNING( 0, "Warning" ),
    ERROR( -1, "Error" );

    public final int code;
    public final String message;

    Status( int code, String message )
    {
        this.code = code;
        this.message = message;
    }
}
