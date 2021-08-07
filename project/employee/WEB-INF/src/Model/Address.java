package Model;

public class Address {
    private int id;
    private int doorNo;
    private String street;
    private String city;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setDoorNo(int doorNo){
        this.doorNo = doorNo;
    }
    public int getDoorNo(){
        return doorNo;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getStreet(){
        return street;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return city;
    }

}
