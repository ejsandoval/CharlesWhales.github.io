package com.generator;

import com.beans.Price;
import database.writeprices.PriceWriter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


/** 
 Generate pseudo-random floating point values.
 Generates numbers between [0.07 - 0.22]
 for the value of kWh.
 **/
public class RandomGenerator implements Runnable {

    double MIN = 0.07f; 
    double AVERAGE = 0.15f;

    private Random fRandom = new Random();

    private double getValue(double min, double average){
      return min + fRandom.nextFloat() * average;
    }
    
    @Override
    public void run(){

        
        // returns the timestamp of the generation
        Date date = new Date();
        Timestamp current_time = new Timestamp(date.getTime());
        
        // assuming that we have 10 providers
        for (int providerID = 1; providerID <= 4; providerID++){
            
            // generates the returning_price for the database
            RandomGenerator kWh_value = new RandomGenerator();
            double value = kWh_value.getValue(MIN, AVERAGE);
  
            PriceWriter pw = new PriceWriter();
            Price price = new Price();
            price.setProviderID(providerID);
            price.setTimestamp(current_time);
            price.setValue(value);
            
            pw.writeEntry(price);
            
        }
    }
} 