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

package org.upwork.prototype.domain.criteria;

import org.upwork.prototype.domain.enums.SortDirection;

import java.util.List;

/**
 * Customer Search Criteria
 *
 * @author prasadm
 * @since 29 May 2022
 */

public class CustomerSearchCriteria
{
    private List<Long> ids;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String country;
    private int start;
    private int page;
    private int size;
    private String sortBy;
    private SortDirection sortDirection;

    public CustomerSearchCriteria()
    {
        this.size = 10;
        this.page = 0;
        this.start = 0;
    }

    public CustomerSearchCriteria( int start, int page, int size, String sortBy, SortDirection sortDirection )
    {
        this.start = start;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public List<Long> getIds()
    {
        return ids;
    }

    public void setIds( List<Long> ids )
    {
        this.ids = ids;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode( String zipCode )
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart( int start )
    {
        this.start = start;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage( int page )
    {
        this.page = page;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize( int size )
    {
        this.size = size;
    }

    public String getSortBy()
    {
        return sortBy;
    }

    public void setSortBy( String sortBy )
    {
        this.sortBy = sortBy;
    }

    public SortDirection getSortDirection()
    {
        return sortDirection;
    }

    public void setSortDirection( SortDirection sortDirection )
    {
        this.sortDirection = sortDirection;
    }
}
