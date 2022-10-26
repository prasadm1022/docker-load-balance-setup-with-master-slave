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
