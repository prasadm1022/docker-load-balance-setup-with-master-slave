package org.upwork.prototype.domain.enums;

/**
 * Sort Direction
 *
 * @author prasadm
 * @since 14 June 2022
 */

public enum SortDirection
{
    ASC( "asc" ),
    DESC( "desc" );

    private final String direction;

    SortDirection( String direction )
    {
        this.direction = direction;
    }
}
