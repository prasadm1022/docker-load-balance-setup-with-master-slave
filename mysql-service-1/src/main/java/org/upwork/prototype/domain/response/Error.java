package org.upwork.prototype.domain.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prasadm
 * @since 29 May 2022
 */

public class Error
{
    private long code;
    private String message;
    private String details;
    private List<String> errors;

    public Error()
    {
        errors = new ArrayList<>();
    }

    public long getCode()
    {
        return code;
    }

    public void setCode( long code )
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails( String details )
    {
        this.details = details;
    }

    public List<String> getErrors()
    {
        return errors;
    }

    public void setErrors( List<String> errors )
    {
        this.errors = errors;
    }
}
