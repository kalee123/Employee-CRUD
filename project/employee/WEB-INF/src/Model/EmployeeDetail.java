package Model;

public class EmployeeDetail {
    Employee employee;
    Address address;

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    public void setAddress(Address address){
        this.address = address;
    }
    public Employee getEmployee(){
        return employee;
    }
    public Address getAddress(){
        return address;
    }
}
