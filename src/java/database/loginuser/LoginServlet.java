package database.loginuser;

import com.filter.LoginFilter;
        
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.naming.Context;
import javax.naming.InitialContext;



public class LoginServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        String message = null;
        PreparedStatement prep_state = null;
        ResultSet resultSet = null;
        boolean login = false;
        Connection myConnection = null;
        
        LoginFilter loggedin = new LoginFilter();

        String username = request.getParameter("userName");
        String password = request.getParameter("userPass");
        

        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/eobserve");
            myConnection = datasource.getConnection();
            prep_state = myConnection.prepareStatement("INSERT INTO user (userName, userPass) VALUES(?,?)");
            prep_state.setString(1,username);
            prep_state.setString(2,password);
            String sql = ("SELECT * FROM user WHERE userName = '"+username+"' and userPass = '"+password+"'");
            resultSet = prep_state.executeQuery(sql);
            login = resultSet.next();
    
        } catch (Exception e) {
            throw new ServletException("Login failed", e);
            
        } finally { 
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (prep_state != null) try { prep_state.close(); } catch (SQLException ignore) {}
            if (myConnection != null) try { myConnection.close(); } catch (SQLException ignore) {}
        }
        
        if (login) {
            message = "Welcome to my First Web Application!!!";
            // using cookies            
            Cookie cookie = new Cookie("userName", username);
            cookie.setMaxAge(60*60*365);
            response.addCookie(cookie);
            // using session
            HttpSession session = request.getSession();
            session.setAttribute("userName", username);
            session.setMaxInactiveInterval(60*60*24);           
            
            response.sendRedirect(request.getContextPath() + "/home");
//            request.getRequestDispatcher("/home.jsp").forward(request, response);

        }
        else {
            //sets the message in request scope
            message =  "Your Username/Password combination was incorrect!\nPlease try again!" ;
            request.setAttribute("ErrorLogin", message);
            
            //forwards to the error page
            request.getRequestDispatcher("/messages/ErrorLogin.jsp").forward(request, response);
        }
//        myConnection.close();
    }

}