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

package org.upwork.prototype.util;

import org.springframework.http.ResponseEntity;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseWrapper;

/**
 * Response Util
 *
 * @author prasadm
 * @since 14 June 2022
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
