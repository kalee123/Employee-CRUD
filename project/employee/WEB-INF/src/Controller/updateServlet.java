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
import Model.*;
import com.google.gson.*;

@WebServlet("/update") 
public class updateServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException  {  
        
        int eid = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        
        int doorNo = Integer.parseInt(request.getParameter("doorNo"));
        String street = request.getParameter("street");
        String city = request.getParameter("city");

        Employee employee = new Employee();
        employee.setId(eid);
        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setGender(gender);
        employee.setDOB(dob);
        employee.setEmail(email);
        employee.setRole(role);
        
        Address address = new Address();
        address.setId(eid);
        address.setDoorNo(doorNo);
        address.setStreet(street);
        address.setCity(city); 

        EmployeeDAO.updateEmployee(employee,address);
                
        response.sendRedirect("index.jsp");
    }
}
