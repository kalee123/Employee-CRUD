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

@WebServlet("/search") 
public class search extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException  {  

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        
        String result = EmployeeDAO.search(data);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(result);
        out.flush();
    }
}
