package database.writeprices;

import com.generator.RandomGenerator;
import com.initialization.InitializationRoutine;

import com.beans.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class PriceWriter {
    
    public void writeEntry(Price price){
            
        Connection myConnection = null;
        PreparedStatement prep_state;
        		
        try {    
            //make the connection with my database
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/eobserve");
            myConnection = datasource.getConnection();

            // insert into table priceentry the required fields
            String query = "INSERT INTO priceentry (Price, Timestamp, ProviderID) VALUES(?,?,?)";
            prep_state = myConnection.prepareStatement(query);
            
            prep_state.setDouble(1,price.getValue());
            prep_state.setTimestamp(2,price.getTimestamp());
            prep_state.setInt(3,price.getProviderID());

            int row = prep_state.executeUpdate();

            if(row > 0) {
//                System.out.println("New value posted!");
            }
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
