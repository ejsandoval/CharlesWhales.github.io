import com.generator.RandomGenerator;
import com.initialization.InitializationRoutine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;
import com.tasks.PriceGenerator;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {

    private ServletContext context = null;
    private Connection dbConnection = null; 

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("in contextDestroyed()");
        context = null;
        
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("in contextInitialized()");

        InitializationRoutine initRoutine = new InitializationRoutine();
        initRoutine.purgeHistory();
        initRoutine.createHistory();
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new RandomGenerator(), 3600, 3600, SECONDS);

        context = event.getServletContext();
        
        try {
            //load jdbc driver  
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
        } catch (Exception ex) {
            System.out.println("Something went wrong!");
            System.out.println(ex);
        }
        
        try {
            
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/eobserve?" +
                                                   "user=lamprini&password=12345");
        
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        context.setAttribute("dbConn", dbConnection);
    }
}