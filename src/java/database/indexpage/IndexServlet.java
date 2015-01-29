package database.indexpage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class IndexServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        PreparedStatement prep_state = null;
        ResultSet resultSet = null;
        Connection myConnection = null;
        double price=0;
        ArrayList<Double> list = new ArrayList<Double>();
        
        try {     
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/eobserve");
            myConnection = datasource.getConnection();
            
            for(int provider = 1; provider < 5; provider++){
                String sql = "SELECT Price FROM priceentry WHERE ProviderID = '"+provider+"' ORDER BY Id DESC LIMIT 1 ";
                prep_state = myConnection.prepareStatement(sql);
                resultSet = prep_state.executeQuery(sql);
                
                while (resultSet.next()){
                    price = resultSet.getDouble("Price");
//                    System.out.println("PRICE IS :" + price);
                }  
                list.add(price);
            }

            request.setAttribute("Price", list);
            request.getRequestDispatcher("/home.jsp").forward(request, response); 

        } catch (Exception e) {
            throw new ServletException(e);
            
        } finally { 
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (prep_state != null) try { prep_state.close(); } catch (SQLException ignore) {}
            if (myConnection != null) try { myConnection.close(); } catch (SQLException ignore) {}
        }    
    }   
}
