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

package org.upwork.prototype.domain.response;

/**
 * Response Error
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class ResponseError extends Exception
{
    private HttpStatus httpStatus;
    private Status status;
    private String message;
    private APIError apiError;
    private Exception exception;
    private ErrorLayer errorLayer;
    private ErrorSource errorSource;

    public ResponseError( Exception exception )
    {
        this.status = Status.ERROR;
        this.exception = exception;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = exception.getMessage();
        this.errorSource = ErrorSource.SERVER_ERROR;
        this.errorLayer = ErrorLayer.API_LAYER;
    }

    public ResponseError( APIError apiError )
    {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.status = Status.ERROR;
        this.apiError = apiError;
        this.errorSource = ErrorSource.CLIENT_ERROR;
        this.errorLayer = ErrorLayer.API_LAYER;
    }

    public ResponseError( ErrorSource errorSource, APIError apiError, HttpStatus httpStatus )
    {
        this.httpStatus = httpStatus;
        this.status = Status.ERROR;
        this.apiError = apiError;
        this.errorSource = errorSource;
        this.errorLayer = ErrorLayer.API_LAYER;
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus( HttpStatus httpStatus )
    {
        this.httpStatus = httpStatus;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus( Status status )
    {
        this.status = status;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public APIError getApiError()
    {
        return apiError;
    }

    public void setApiError( APIError apiError )
    {
        this.apiError = apiError;
    }

    public Exception getException()
    {
        return exception;
    }

    public void setException( Exception exception )
    {
        this.exception = exception;
    }

    public ErrorLayer getErrorLayer()
    {
        return errorLayer;
    }

    public void setErrorLayer( ErrorLayer errorLayer )
    {
        this.errorLayer = errorLayer;
    }

    public ErrorSource getErrorSource()
    {
        return errorSource;
    }

    public void setErrorSource( ErrorSource errorSource )
    {
        this.errorSource = errorSource;
    }
}
