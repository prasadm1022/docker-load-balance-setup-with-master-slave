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

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upwork.prototype.domain.criteria.CustomerSearchCriteria;
import org.upwork.prototype.domain.dto.Customer;
import org.upwork.prototype.domain.response.ResponseWrapper;

import java.util.Map;

/**
 * Customer REST Controller
 *
 * @author prasadm
 * @since 29 May 2022
 */

@RequestMapping( "/v1/customer" )
public interface ICustomerController
{
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<Customer>> search( CustomerSearchCriteria customerSearchCriteria );

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<Customer>> saveCustomer( @RequestBody Customer customer );

    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<Customer>> updateCustomer( @RequestBody Customer customer );

    @PatchMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<Customer>> patchCustomer( @PathVariable long id, @RequestBody Map<Object,Object> fields );

    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ResponseWrapper<Void>> deleteCustomer( @PathVariable long id );
}
