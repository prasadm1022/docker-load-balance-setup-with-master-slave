package org.upwork.prototype.util;

import org.springframework.http.ResponseEntity;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseWrapper;

/**
 * Response Util
 *
 * @author prasadm
 * @since 29 May 2022
 */

public class ResponseUtil
{
    private ResponseUtil()
    {
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> wrap( Response<T> response )
    {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>( response );
        return ResponseEntity.status( responseWrapper.getHttpStatus().value ).body( responseWrapper );
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> wrap( Exception error )
    {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>( error );
        return ResponseEntity.status( responseWrapper.getHttpStatus().value ).body( responseWrapper );
    }
}
