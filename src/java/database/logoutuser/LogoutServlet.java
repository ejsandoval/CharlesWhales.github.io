package database.logoutuser;

import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession; 

public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
    
        String message = "You are successfully Logged out!\n Please Sign in to Continue!";
        HttpSession session = request.getSession(false);
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpResponse.setDateHeader("Expires", 0); // Proxies.
        
        Cookie loginCookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    loginCookie = cookie;
                    break;
                }
            }
        } 
        // delete existing cookies
        if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        // invalidate session
        if(session != null){
            session.removeAttribute("userName");
            session.invalidate();
            request.setAttribute("Logout", message);

            //forwards to the logout page
            response.sendRedirect(request.getContextPath() + "/home");
        }
        response.getWriter().close();  
    }  
}

       

            