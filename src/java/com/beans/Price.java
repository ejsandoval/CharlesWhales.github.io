package com.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Price implements Serializable{
    
    private int providerID;
    private double value;
    private Timestamp timestamp;
    private DateFormat dateformat;

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
     public DateFormat getDateFormat() {
        return dateformat;
    }

    public void setDateFormat(DateFormat dateformat) {
        this.dateformat = dateformat;
    }
    
}


    