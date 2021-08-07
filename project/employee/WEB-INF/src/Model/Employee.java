package Model;

public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String dob;
    private String gender;
    private String email;
    private String role;
    private String city;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setDOB(String dob){
        this.dob = dob;
    }
    public String getDOB(){
        return dob;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getCity(){
        return city;
    }
        
}
