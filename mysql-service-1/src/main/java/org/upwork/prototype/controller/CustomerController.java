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
