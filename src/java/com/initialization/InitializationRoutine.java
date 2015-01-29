package com.initialization;

import com.beans.Price;
import database.writeprices.PriceWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class InitializationRoutine {
    double MIN = 0.07f; 
    double AVERAGE = 0.15f;

    private Random fRandom = new Random();

    private double getValue(double min, double average){
      return min + fRandom.nextFloat() * average;
    }    
    
    public void createHistory(){        
        for (int i=1; i<= 30*24; i++){
            Timestamp i_HoursAgo = new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)*i );
            
            // assuming that we have 10 providers
            for (int providerID = 1; providerID <= 4; providerID++){

                // generates the returning_price for the database
                InitializationRoutine kWh_value = new InitializationRoutine();
                double value = kWh_value.getValue(MIN, AVERAGE);

                PriceWriter pw = new PriceWriter();
                Price price = new Price();
                price.setProviderID(providerID);
    //            price.setDateFormat(dateFormat);
                price.setTimestamp(i_HoursAgo);
                price.setValue(value);

                pw.writeEntry(price);
            }
        }
    }
    
    public void purgeHistory(){
        Connection myConnection = null;
        Statement create_state = null;
        		
        try {    
            //make the connection with my database
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/eobserve");
            myConnection = datasource.getConnection();

            // insert into table priceentry the required fields
            String query = "DELETE FROM priceentry";
            create_state = myConnection.createStatement();
            create_state.execute(query);
            
            System.out.println("ALL VALUES DELETED!! ");
            
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
            
        } finally { 
            if (myConnection != null){
                //closes the database connection
                try {
                    myConnection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}
