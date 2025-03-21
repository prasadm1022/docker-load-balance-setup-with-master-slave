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

package org.upwork.prototype.mapper;

import org.springframework.stereotype.Component;
import org.upwork.prototype.domain.dto.Customer;
import org.upwork.prototype.entity.OpCustomer;

/**
 * Customer Mapper
 *
 * @author prasadm
 * @since 29 May 2022
 */

@Component
public class CustomerMapper
{
    public Customer toDto( OpCustomer opCustomer )
    {
        Customer customer = new Customer();

        customer.setId( opCustomer.getId() );
        customer.setFirstName( opCustomer.getFirstName() );
        customer.setLastName( opCustomer.getLastName() );
        customer.setAddress( opCustomer.getAddress() );
        customer.setZipCode( opCustomer.getZipCode() );
        customer.setCity( opCustomer.getCity() );
        customer.setCountry( opCustomer.getCountry() );

        return customer;
    }

    public OpCustomer toEntity( Customer customer )
    {
        OpCustomer opCustomer = new OpCustomer();

        opCustomer.setId( customer.getId() );
        opCustomer.setFirstName( customer.getFirstName() );
        opCustomer.setLastName( customer.getLastName() );
        opCustomer.setAddress( customer.getAddress() );
        opCustomer.setZipCode( customer.getZipCode() );
        opCustomer.setCity( customer.getCity() );
        opCustomer.setCountry( customer.getCountry() );

        return opCustomer;
    }
}
