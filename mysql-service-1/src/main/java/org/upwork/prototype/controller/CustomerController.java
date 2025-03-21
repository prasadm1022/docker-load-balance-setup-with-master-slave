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

package org.upwork.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.upwork.prototype.domain.criteria.CustomerSearchCriteria;
import org.upwork.prototype.domain.dto.Customer;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseError;
import org.upwork.prototype.domain.response.ResponseWrapper;
import org.upwork.prototype.service.ICustomerService;
import org.upwork.prototype.util.ResponseUtil;

import java.util.Map;

/**
 * Customer REST Controller
 *
 * @author prasadm
 * @since 29 May 2022
 */

@RestController
public class CustomerController implements ICustomerController
{
    @Autowired
    private ICustomerService customerService;

    @Override
    public ResponseEntity<ResponseWrapper<Customer>> search( CustomerSearchCriteria customerSearchCriteria )
    {
        try
        {
            Response<Customer> response = customerService.searchCustomers( customerSearchCriteria );
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<Customer>> saveCustomer( Customer customer )
    {
        try
        {
            Response<Customer> response = customerService.saveCustomer( customer );
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<Customer>> updateCustomer( Customer customer )
    {
        try
        {
            Response<Customer> response = customerService.updateCustomer( customer );
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<Customer>> patchCustomer( long id, Map<Object,Object> fields )
    {
        try
        {
            Response<Customer> response = customerService.patchCustomer( id, fields );
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<Void>> deleteCustomer( long id )
    {
        try
        {
            Response<Void> response = customerService.deleteCustomer( id );
            return ResponseUtil.wrap( response );
        }
        catch( ResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
