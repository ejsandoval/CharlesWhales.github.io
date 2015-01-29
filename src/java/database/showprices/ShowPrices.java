package database.showprices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPrices extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {


        int period = Integer.parseInt(request.getParameter("period"));
        
        request.setAttribute("period", period);
        
        request.getRequestDispatcher("/show_prices.jsp").forward(request, response);
    
    }
}
