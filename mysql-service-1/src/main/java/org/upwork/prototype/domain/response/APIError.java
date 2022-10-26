package org.upwork.prototype.domain.response;

import org.upwork.prototype.util.Constants;

/**
 * Custom API Error Messages
 *
 * @author prasadm
 * @since 29 May 2022
 */

public enum APIError
{
    NO_ERROR( 0, "", "" ),

    // backend exceptions
    ERR_USER_SEARCH_ERROR( 10000, "Search failed", "Search failed" ),
    ERR_USER_SAVE_ERROR( 10001, "Save failed", "Save failed" ),
    ERR_USER_DELETE_ERROR( 10002, "Delete failed", "Delete failed" ),

    // common exceptions
    SAVING_SQL_EXCEPTION( 900, "Saving SQL Exception", Constants.EXCEPTION ),
    REMOTE_EXCEPTION( 901, "Remote Method Invocation Exception", Constants.EXCEPTION ),
    NULL_POINTER_EXCEPTION( 902, "NullPointer Exception", Constants.EXCEPTION ),
    NUMBER_FORMAT_EXCEPTION( 903, "Number Format Exception", Constants.EXCEPTION ),
    ILLEGAL_ARGUMENT_EXCEPTION( 904, "IllegalArgument Exception", Constants.EXCEPTION ),
    INDEX_OUT_OF_BOUNDS_EXCEPTION( 905, "IndexOutOfBounds Exception", Constants.EXCEPTION ),
    NO_SUCH_METHOD_EXCEPTION( 906, "NoSuchMethod Exception", Constants.EXCEPTION ),
    ILLEGAL_ACCESS_EXCEPTION( 907, "IllegalAccess Exception", Constants.EXCEPTION ),
    INVOCATION_TARGET_EXCEPTION( 908, "InvocationTarget Exception", Constants.EXCEPTION ),
    BAD_REQUEST_EXCEPTION( 909, "Bad Request Exception", Constants.EXCEPTION ),
    INVALID_DATA_ACCESS_RESOURCE_EXCEPTION( 910, "Invalid Data Access Resource Usage Exception", Constants.EXCEPTION ),
    UNKNOWN_EXCEPTION( 999, "Unknown Exception", Constants.EXCEPTION );

    public final int code;
    public final String details;
    public final String message;

    APIError( int code, String details, String message )
    {
        this.code = code;
        this.details = details;
        this.message = message;
    }
}
