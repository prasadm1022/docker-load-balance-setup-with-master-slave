package org.upwork.prototype.domain.response;

/**
 * Page Data
 *
 * @author prasadm
 * @since 14 June 2022
 */

public class PageData
{
    private int count;
    private int total;

    public PageData()
    {
    }

    public PageData( int count, int total )
    {
        this.count = count;
        this.total = total;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount( int count )
    {
        this.count = count;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal( int total )
    {
        this.total = total;
    }
}
