package database.registeruser;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.naming.Context;
import javax.naming.InitialContext;


public class RegisterServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        Connection myConnection = null;
        PreparedStatement prep_state = null;
        String message = null;
        HttpSession session = request.getSession();
 
        String username = request.getParameter("userName");
        String password = request.getParameter("userPass");
		
        try{

            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/eobserve");
            myConnection = datasource.getConnection();
            prep_state = myConnection.prepareStatement("INSERT INTO user (userName, userPass) VALUES(?,?)");
            prep_state.setString(1,username);
            prep_state.setString(2,password);
            // using cookies            
            Cookie cookie = new Cookie("userName", username);
            cookie.setMaxAge(60*60*365);
            response.addCookie(cookie);
            // using session
            session.setAttribute("userName", username);
            session.setMaxInactiveInterval(60*60*24);

            int row = prep_state.executeUpdate();

        } catch (Exception ex) {
            message = "ERROR: " + ex.getMessage();
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
            
            //forwards to the message page
            response.sendRedirect(request.getContextPath() + "/home");
//            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
 
        }
    }
}