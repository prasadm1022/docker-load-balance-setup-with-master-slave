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
