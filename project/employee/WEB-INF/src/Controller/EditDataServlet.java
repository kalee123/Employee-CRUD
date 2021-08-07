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
import com.google.gson.*;

@WebServlet("/editData") 
public class EditDataServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException  {  

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        // reading request payload
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String json = buffer.toString();

        //desirializing json request params
        Gson gson = new Gson();
        ReqType ob = gson.fromJson(json, ReqType.class);
        
        String result;
        if(ob.type.equals("update")){
            result=EmployeeDAO.getEmployeeDetail(ob.id);    
        }
        else{
            result=EmployeeDAO.getId();
        }

        //returning response
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(result);
        out.flush();
    }
}

class ReqType{
    String type;
    int id;
}
