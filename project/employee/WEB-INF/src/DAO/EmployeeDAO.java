package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import javax.sql.DataSource;  
import javax.naming.*;
import java.util.*;
import com.google.gson.*;
import Model.*;

public class EmployeeDAO {

    // Insert into table
    public static void insertEmployee(Employee emp,Address address){
        int role=0;
        String []roles=new String[]{"manager","java developer","team leader","trainee"};
        for(int i=0;i<4;i++){
            if(roles[i].equals(emp.getRole()))
                role=i+1;
        }

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            Connection con = ds.getConnection();

            String empSQL = "Insert INTO employees VALUES ( ? , ? , ? , ? , ? , ? , ? )";
            String addressSQL = "Insert INTO address VALUES ( ? , ? , ? , ? )";

            PreparedStatement prepStmt1 = con.prepareStatement(empSQL); 
            PreparedStatement prepStmt2 = con.prepareStatement(addressSQL);
            
            prepStmt1.setInt(1, emp.getId());
            prepStmt1.setString(2, emp.getFirstname());
            prepStmt1.setString(3, emp.getLastname());
            prepStmt1.setDate(4, Date.valueOf(emp.getDOB()));
            prepStmt1.setString(5, emp.getGender()); 
            prepStmt1.setString(6, emp.getEmail());
            prepStmt1.setInt(7, role);
            prepStmt1.executeUpdate();

            prepStmt2.setInt(1, emp.getId());
            prepStmt2.setInt(2, address.getDoorNo());
            prepStmt2.setString(3, address.getStreet());
            prepStmt2.setString(4, address.getCity()); 
            prepStmt2.executeUpdate();

        }catch(Exception e){
            System.out.println(e);
        }        
    }

    // Update employe details
    public static void updateEmployee(Employee emp,Address address){
        int role=0;
        String []roles=new String[]{"manager","java developer","team leader","trainee"};
        for(int i=0;i<4;i++){
            if(roles[i].equals(emp.getRole()))
                role=i+1;
        }

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            Connection con = ds.getConnection();

            String empSQL = "UPDATE employees SET first_name=?,last_name=?,dob=?,gender=?,email=?,role_id=? WHERE emp_id="+emp.getId();
            String addressSQL = "UPDATE address SET  door_no=?,street=?,city=? WHERE emp_id="+emp.getId();
    
            PreparedStatement prepStmt1 = con.prepareStatement(empSQL);
            PreparedStatement prepStmt2 = con.prepareStatement(addressSQL);
            
            prepStmt1.setString(1, emp.getFirstname());
            prepStmt1.setString(2, emp.getLastname());
            prepStmt1.setDate(3, Date.valueOf(emp.getDOB()));
            prepStmt1.setString(4, emp.getGender()); 
            prepStmt1.setString(5, emp.getEmail());
            prepStmt1.setInt(6, role);
            prepStmt1.executeUpdate();
    
            prepStmt2.setInt(1, address.getDoorNo());
            prepStmt2.setString(2, address.getStreet());
            prepStmt2.setString(3, address.getCity()); 
            prepStmt2.executeUpdate();
    
        }catch(Exception e){
            System.out.println(e);
        }        
    
    }

    // Edit employee
    public static String getEmployeeDetail(int id){
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            Connection con = ds.getConnection();

            String SQL = "SELECT * FROM employees JOIN role ON employees.role_id=role.role_id JOIN address ON employees.emp_id=address.emp_id WHERE employees.emp_id="+id;
            PreparedStatement prepStmt = con.prepareStatement(SQL);
           
            ResultSet rs = prepStmt.executeQuery();
            EmployeeDetail data = new EmployeeDetail();

            rs.next();
            Employee emp = new Employee();
            emp.setId(Integer.parseInt(rs.getString("emp_id")));
            emp.setFirstname(rs.getString("first_name"));
            emp.setLastname(rs.getString("last_name"));
            emp.setEmail(rs.getString("email"));
            emp.setRole(rs.getString("role_name"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
            emp.setDOB(dateFormat.format(rs.getDate("dob")));
            emp.setGender(rs.getString("gender"));

            Address address=new Address();
            address.setDoorNo(emp.getId());
            address.setDoorNo(rs.getInt("door_no"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            
            data.setEmployee(emp);
            data.setAddress(address);

            rs.close();
            prepStmt.close();
            con.close();
            Gson gson = new Gson();
            return gson.toJson(data);

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    // Search based on viewable columns in JSP
    public static String search(String param){
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            Connection con = ds.getConnection();

            String SQL = "SELECT employees.emp_id,first_name,last_name,email,role_name,address.city from employees INNER JOIN role on employees.role_id=role.role_id INNER JOIN address on employees.emp_id=address.emp_id WHERE cast(employees.emp_id as CHAR) LIKE '%"+param+"%' OR first_name LIKE '%"+param+"%' OR last_name LIKE '%"+param+"%' OR email LIKE '%"+param+"%' OR role_name LIKE '%"+param+"%' OR city LIKE '%"+param+"%'";
            PreparedStatement prepStmt = con.prepareStatement(SQL);
           
            ResultSet rs = prepStmt.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while(rs.next()){
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(rs.getString("emp_id")));
                emp.setFirstname(rs.getString("first_name"));
                emp.setLastname(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setRole(rs.getString("role_name"));
                emp.setCity(rs.getString("city"));
                list.add(emp);
            }

            rs.close();
            prepStmt.close();
            con.close();
            Gson gson = new Gson();
            return gson.toJson(list);

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    // Delete rows 
    public static String delete(int id){
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            Connection con = ds.getConnection();

            String deleteFK = "DELETE FROM address USING employees WHERE employees.emp_id = address.emp_id and employees.emp_id="+id;
            String deletePK = "DELETE FROM employees WHERE emp_id="+id;
            
            PreparedStatement prepStmt1 = con.prepareStatement(deleteFK);
            PreparedStatement prepStmt2 = con.prepareStatement(deletePK);
            
            prepStmt1.executeUpdate();
            prepStmt2.executeUpdate();

            String fetch="SELECT employees.emp_id,first_name,last_name,email,role_name,city FROM employees INNER JOIN role on employees.role_id=role.role_id JOIN address ON employees.emp_id = address.emp_id ";
            
            PreparedStatement prepStmt = con.prepareStatement(fetch);
            ResultSet rs = prepStmt.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while(rs.next()){
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(rs.getString("emp_id")));
                emp.setFirstname(rs.getString("first_name"));
                emp.setLastname(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setRole(rs.getString("role_name"));
                emp.setCity(rs.getString("city"));
                list.add(emp);
            }

            rs.close();
            prepStmt.close();
            con.close();
            Gson gson = new Gson();
            return gson.toJson(list);

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    //Return next employee id
    public static String getId(){
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            Connection con = ds.getConnection();

            PreparedStatement prepStatement = con.prepareStatement("SELECT MAX(emp_id) FROM employees ");
            ResultSet rs = prepStatement.executeQuery();
            rs.next();
            int count = rs.getInt(1)+1;
            rs.close();
            con.close();
            Gson gson=new Gson();
            return gson.toJson(count);
        }catch(Exception e){
            System.out.println(e);
        }
        return "0";
    }
    
}
