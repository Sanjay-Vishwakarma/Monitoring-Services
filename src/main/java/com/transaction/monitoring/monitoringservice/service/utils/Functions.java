package com.transaction.monitoring.monitoringservice.service.utils;


/**
 * Created by Admin on 7/30/20.
 */

public class Functions {

    public boolean isValueNull(String str)
    {
        if(str != null && !str.equals("null") && !str.equals(""))
        {
            return true;
        }
        return false;
    }
}
