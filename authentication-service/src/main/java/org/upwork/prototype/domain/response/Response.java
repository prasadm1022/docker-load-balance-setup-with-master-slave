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

import java.util.ArrayList;
import java.util.List;

/**
 * Response
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class Response<T>
{
    private PageData pageData;
    private List<T> data;
    private HttpStatus httpStatus;
    private Status status;
    private String message;
    private ResponseError responseError;

    public Response()
    {
        this.status = Status.SUCCESS;
        this.pageData = new PageData();
        this.data = new ArrayList<>();
        this.httpStatus = HttpStatus.OK;
    }

    public Response( Status status )
    {
        this.status = status;
        this.pageData = new PageData();
        this.data = new ArrayList<>();
        if( status == Status.SUCCESS )
        {
            this.httpStatus = HttpStatus.OK;
        }
        else if( status == Status.ERROR )
        {
            this.httpStatus = HttpStatus.BAD_REQUEST;
        }
        else
        {
            this.httpStatus = HttpStatus.OK;
        }
    }

    public Response( APIError apiError )
    {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.status = Status.ERROR;
        this.responseError = new ResponseError( apiError );
    }

    public Response( T data )
    {
        this();
        this.data.add( data );
        this.pageData.setCount( 1 );
        this.pageData.setTotal( 1 );
    }

    public Response( T data, String message )
    {
        this();
        this.data.add( data );
        this.pageData.setCount( 1 );
        this.pageData.setTotal( 1 );
        setMessage( message );
    }

    public Response( List<T> data )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( data.size() );
    }

    public Response( List<T> data, String message )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( data.size() );
        setMessage( message );
    }

    public Response( List<T> data, int totalCount )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( totalCount );
    }

    public Response( List<T> data, int totalCount, String message )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( totalCount );
        setMessage( message );
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

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public ResponseError getResponseError()
    {
        return responseError;
    }

    public void setResponseError( ResponseError responseError )
    {
        this.responseError = responseError;
    }
}
