package database.fetchprices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class FetchPricesServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {


        int period = Integer.parseInt(request.getParameter("period"));
        int provider = Integer.parseInt(request.getParameter("provider"));
    
        PreparedStatement prep_state = null;
        ResultSet resultSet = null;
        Connection myConnection = null;
        
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/eobserve");
            myConnection = datasource.getConnection();

            String result;
            if (period > 1){
                result = "[";
                for (int i=0; i < period; i++){
                    double finalPrices = 0;
                    double average = 0;
                    int offset = 24 * i ;
                    String sql = "SELECT Price FROM priceentry "
                            + "WHERE ProviderID = '" + provider + "' "
                            + "ORDER BY Timestamp DESC "
                            + "LIMIT 24 "
                            + "OFFSET " + offset;
                    prep_state = myConnection.prepareStatement(sql);
                    resultSet = prep_state.executeQuery(sql);

                    while (resultSet.next()){
                        double oneHourPrice = resultSet.getDouble("Price");
                        finalPrices = finalPrices + oneHourPrice;
                    }
                    average = finalPrices / 24;
                    result = result + average;
                    
                    if(i < period - 1) {
                        result = result + ", ";
                    }
                }
                result = result + "]";            
            }
            else{
                String sql = "SELECT Price FROM priceentry WHERE ProviderID = '"+provider+"' ORDER BY Timestamp DESC LIMIT 24";
                prep_state = myConnection.prepareStatement(sql);
                resultSet = prep_state.executeQuery(sql);

                result = "[";
                while (resultSet.next()){
                    String price = resultSet.getString("Price");
                    if (result.length() > 1) {
                        result = result + ", ";
                    }
                    result = result + price;
                }
                result = result + "]";
            }
            
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(result);
            
            
            
        } catch (Exception e) {
            throw new ServletException(e);
            
        } finally { 
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (prep_state != null) try { prep_state.close(); } catch (SQLException ignore) {}
            if (myConnection != null) try { myConnection.close(); } catch (SQLException ignore) {}
        }
    
    }
}
