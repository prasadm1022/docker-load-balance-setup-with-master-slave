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

package org.upwork.prototype.service;

import org.upwork.prototype.domain.criteria.CustomerSearchCriteria;
import org.upwork.prototype.domain.dto.Customer;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseError;

import java.util.Map;

/**
 * Customer Service
 *
 * @author prasadm
 * @since 29 May 2022
 */

public interface ICustomerService
{
    Response<Customer> searchCustomers( CustomerSearchCriteria customerSearchCriteria ) throws ResponseError;

    Response<Customer> saveCustomer( Customer customer ) throws ResponseError;

    Response<Customer> updateCustomer( Customer customer ) throws ResponseError;

    Response<Customer> patchCustomer( long id, Map<Object,Object> fields ) throws ResponseError;

    Response<Void> deleteCustomer( long id ) throws ResponseError;
}
