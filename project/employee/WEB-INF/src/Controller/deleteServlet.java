package Controller;

import java.io.IOException;
import java.io.*;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import DAO.EmployeeDAO;
import Model.Employee;
import Model.Address;

@WebServlet("/delete") 
public class deleteServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException  {  
        String id = request.getParameter("id");
        String JSON = EmployeeDAO.delete(Integer.parseInt(id));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.println(JSON);
    }
    
}
