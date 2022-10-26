package org.upwork.prototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.upwork.prototype.domain.criteria.CustomerSearchCriteria;
import org.upwork.prototype.domain.dto.Customer;
import org.upwork.prototype.domain.enums.SortDirection;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseError;
import org.upwork.prototype.domain.response.Status;
import org.upwork.prototype.entity.OpCustomer;
import org.upwork.prototype.mapper.CustomerMapper;
import org.upwork.prototype.repository.ICustomerRepository;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Customer Service
 *
 * @author prasadm
 * @since 29 May 2022
 */

@Service
public class CustomerService implements ICustomerService
{
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Response<Customer> searchCustomers( CustomerSearchCriteria customerSearchCriteria ) throws ResponseError
    {
        int page = 0;
        if( customerSearchCriteria.getSize() != 0 )
        {
            page = customerSearchCriteria.getStart() / customerSearchCriteria.getSize();
        }
        Sort.Direction sortDirection = ( customerSearchCriteria.getSortDirection() != null && customerSearchCriteria.getSortDirection() == SortDirection.DESC ) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = StringUtils.hasText( customerSearchCriteria.getSortBy() ) ? customerSearchCriteria.getSortBy() : "id";
        customerSearchCriteria.setSize( Integer.MAX_VALUE );
        PageRequest pageRequest = PageRequest.of( page, customerSearchCriteria.getSize(), sortDirection, sortBy );
        try
        {
            Page<OpCustomer> opCustomers = customerRepository.findAll( ( root, criteriaQuery, criteriaBuilder ) ->
            {
                List<Predicate> predicates = new ArrayList<>();
                if( customerSearchCriteria.getIds() != null && !customerSearchCriteria.getIds().isEmpty() )
                {
                    predicates.add( criteriaBuilder.and( root.get( "id" ).in( customerSearchCriteria.getIds() ) ) );
                }
                if( customerSearchCriteria.getFirstName() != null )
                {
                    predicates.add( criteriaBuilder.equal( criteriaBuilder.lower( root.get( "firstName" ) ), customerSearchCriteria.getFirstName().toLowerCase() ) );
                }
                if( customerSearchCriteria.getLastName() != null )
                {
                    predicates.add( criteriaBuilder.equal( criteriaBuilder.lower( root.get( "lastName" ) ), customerSearchCriteria.getLastName().toLowerCase() ) );
                }
                if( customerSearchCriteria.getAddress() != null )
                {
                    predicates.add( criteriaBuilder.equal( criteriaBuilder.lower( root.get( "address" ) ), customerSearchCriteria.getAddress().toLowerCase() ) );
                }
                if( customerSearchCriteria.getZipCode() != null )
                {
                    predicates.add( criteriaBuilder.equal( criteriaBuilder.lower( root.get( "zipCode" ) ), customerSearchCriteria.getZipCode().toLowerCase() ) );
                }
                if( customerSearchCriteria.getCity() != null )
                {
                    predicates.add( criteriaBuilder.equal( criteriaBuilder.lower( root.get( "city" ) ), customerSearchCriteria.getCity().toLowerCase() ) );
                }
                if( customerSearchCriteria.getCountry() != null )
                {
                    predicates.add( criteriaBuilder.equal( criteriaBuilder.lower( root.get( "country" ) ), customerSearchCriteria.getCountry().toLowerCase() ) );
                }
                criteriaQuery.distinct( true );
                return criteriaBuilder.and( predicates.toArray( new Predicate[] {} ) );
            }, pageRequest );
            List<Customer> response = opCustomers.getContent().stream().map( opCustomer -> customerMapper.toDto( opCustomer ) ).collect( Collectors.toList() );
            return new Response<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<Customer> saveCustomer( Customer customer ) throws ResponseError
    {
        try
        {
            OpCustomer saveResponse = customerRepository.save( customerMapper.toEntity( customer ) );
            return new Response<>( customerMapper.toDto( saveResponse ) );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<Customer> updateCustomer( Customer customer ) throws ResponseError
    {
        try
        {
            OpCustomer updateResponse = customerRepository.save( customerMapper.toEntity( customer ) );
            return new Response<>( customerMapper.toDto( updateResponse ) );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<Customer> patchCustomer( long id, Map<Object,Object> fields ) throws ResponseError
    {
        try
        {
            final OpCustomer[] patchResponse = { new OpCustomer() };
            customerRepository.findById( id ).ifPresent( opCustomer -> fields.forEach( ( key, value ) ->
            {
                Field field = ReflectionUtils.findField( OpCustomer.class, ( String ) key );
                if( field != null )
                {
                    field.setAccessible( true );
                    ReflectionUtils.setField( field, opCustomer, value );
                    patchResponse[0] = customerRepository.save( opCustomer );
                }
            } ) );
            return new Response<>( customerMapper.toDto( patchResponse[0] ) );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }

    @Override
    public Response<Void> deleteCustomer( long id ) throws ResponseError
    {
        try
        {
            if( !customerRepository.existsById( id ) )
            {
                return new Response<>( Status.ERROR );
            }
            customerRepository.deleteById( id );
            return new Response<>( Status.SUCCESS );
        }
        catch( Exception ex )
        {
            throw new ResponseError( ex );
        }
    }
}
