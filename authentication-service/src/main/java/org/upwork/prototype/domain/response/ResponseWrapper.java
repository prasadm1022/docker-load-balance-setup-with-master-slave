package org.upwork.prototype.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import javax.ws.rs.BadRequestException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Response Wrapper
 *
 * @author prasadm
 * @since 14 June 2022
 */

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "status", "error", "pageData", "data", "version" } )
public class ResponseWrapper<T>
{
    private static final String API_ERROR_CODE = "API Error Code : ";
    private static final String API_ERROR_CODE_SEPARATOR = " - ";
    private static final String API_VERSION = "v1.0";

    private PageData pageData = new PageData();
    private List<T> data;
    private Status status;
    private Error error;
    private HttpStatus httpStatus;
    private String version = API_VERSION;

    public ResponseWrapper( Response<T> response )
    {
        if( response.getResponseError() != null )
        {
            onError( response.getResponseError() );
        }
        else
        {
            this.status = new Status();
            if( response.getStatus() != null )
            {
                this.status.code = response.getStatus().code;
                this.status.message = response.getStatus().message;
            }
            this.status.description = response.getMessage();
            this.httpStatus = response.getHttpStatus();
            this.data = response.getData();
            this.pageData = response.getPageData();
        }

    }

    public ResponseWrapper( Exception exception )
    {
        onError( exception );
    }

    private static void addErrorDescription( APIError errorCode, List<String> errors )
    {
        errors.add( API_ERROR_CODE + errorCode.code + API_ERROR_CODE_SEPARATOR + errorCode.details );
    }

    private void onError( Exception exception )
    {
        ResponseError responseError;
        if( !( exception instanceof ResponseError ) )
        {
            responseError = new ResponseError( exception );
        }
        else
        {
            responseError = ( ResponseError ) exception;
        }
        this.status = new Status();
        if( responseError.getStatus() != null )
        {
            this.status.code = responseError.getStatus().code;
            this.status.message = responseError.getStatus().message;
        }
        this.status.description = responseError.getMessage();
        this.httpStatus = responseError.getHttpStatus();
        this.pageData = null;
        this.data = null;
        fillErrorInfo( responseError );
    }

    private void fillErrorInfo( ResponseError responseError )
    {
        mapErrorCode( responseError );
    }

    private void mapErrorCode( ResponseError responseError )
    {
        Error newError = new Error();
        long errorCode = ( long ) responseError.getErrorSource().code;
        errorCode = errorCode * 10L + ( long ) responseError.getErrorLayer().code;
        if( responseError.getApiError() == null && responseError.getException() != null )
        {
            Exception ex = responseError.getException();
            ex.printStackTrace();

            Throwable cause = ex.getCause();
            if( cause != null )
            {
                if( cause instanceof SQLException )
                {
                    responseError.setApiError( APIError.SAVING_SQL_EXCEPTION );
                    responseError.setMessage( APIError.SAVING_SQL_EXCEPTION.message );
                }
                else if( cause instanceof RemoteException )
                {
                    responseError.setApiError( APIError.REMOTE_EXCEPTION );
                    responseError.setMessage( APIError.REMOTE_EXCEPTION.message );
                }
                else if( cause instanceof NullPointerException )
                {
                    responseError.setApiError( APIError.NULL_POINTER_EXCEPTION );
                    responseError.setMessage( APIError.NULL_POINTER_EXCEPTION.message );
                }
                else if( cause instanceof NumberFormatException )
                {
                    responseError.setApiError( APIError.NUMBER_FORMAT_EXCEPTION );
                    responseError.setMessage( APIError.NUMBER_FORMAT_EXCEPTION.message );
                }
                else if( cause instanceof IllegalArgumentException )
                {
                    responseError.setApiError( APIError.ILLEGAL_ARGUMENT_EXCEPTION );
                    responseError.setMessage( APIError.ILLEGAL_ARGUMENT_EXCEPTION.message );
                }
                else if( cause instanceof IndexOutOfBoundsException )
                {
                    responseError.setApiError( APIError.INDEX_OUT_OF_BOUNDS_EXCEPTION );
                    responseError.setMessage( APIError.INDEX_OUT_OF_BOUNDS_EXCEPTION.message );
                }
                else if( cause instanceof NoSuchMethodException )
                {
                    responseError.setApiError( APIError.NO_SUCH_METHOD_EXCEPTION );
                    responseError.setMessage( APIError.NO_SUCH_METHOD_EXCEPTION.message );
                }
                else if( cause instanceof IllegalAccessException )
                {
                    responseError.setApiError( APIError.ILLEGAL_ACCESS_EXCEPTION );
                    responseError.setMessage( APIError.ILLEGAL_ACCESS_EXCEPTION.message );
                }
                else if( cause instanceof InvocationTargetException )
                {
                    responseError.setApiError( APIError.INVOCATION_TARGET_EXCEPTION );
                    responseError.setMessage( APIError.INVOCATION_TARGET_EXCEPTION.message );
                }
                else if( cause instanceof BadRequestException )
                {
                    responseError.setApiError( APIError.BAD_REQUEST_EXCEPTION );
                    responseError.setMessage( APIError.BAD_REQUEST_EXCEPTION.message );
                }
                else if( cause instanceof InvalidDataAccessResourceUsageException )
                {
                    responseError.setApiError( APIError.INVALID_DATA_ACCESS_RESOURCE_EXCEPTION );
                    responseError.setMessage( APIError.INVALID_DATA_ACCESS_RESOURCE_EXCEPTION.message );
                }
                else
                {
                    responseError.setApiError( APIError.UNKNOWN_EXCEPTION );
                    responseError.setMessage( APIError.UNKNOWN_EXCEPTION.message );
                }
            }
        }

        errorCode = errorCode * 10000L + ( long ) ( responseError.getApiError() == null ? 0 : responseError.getApiError().code );
        List<String> errors = new ArrayList<>( 2 );
        if( responseError.getApiError() != null && responseError.getApiError().code > 0 )
        {
            addErrorDescription( responseError.getApiError(), errors );
            newError.setDetails( responseError.getApiError().details );
            newError.setMessage( responseError.getApiError().message );
            if( this.status != null && ( this.status.description == null || this.status.description.isEmpty() ) )
            {
                this.status.description = responseError.getApiError().message;
            }
        }

        newError.setErrors( errors );
        newError.setCode( errorCode );
        this.setError( newError );
    }

    public PageData getPageData()
    {
        return pageData;
    }

    public void setPageData( PageData pageData )
    {
        this.pageData = pageData;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData( List<T> data )
    {
        this.data = data;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus( Status status )
    {
        this.status = status;
    }

    public Error getError()
    {
        return error;
    }

    public void setError( Error error )
    {
        this.error = error;
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus( HttpStatus httpStatus )
    {
        this.httpStatus = httpStatus;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    @JsonInclude( JsonInclude.Include.NON_NULL )
    private static class Status
    {
        int code;
        String message;
        String description;

        public int getCode()
        {
            return code;
        }

        public void setCode( int code )
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage( String message )
        {
            this.message = message;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription( String description )
        {
            this.description = description;
        }
    }
}
