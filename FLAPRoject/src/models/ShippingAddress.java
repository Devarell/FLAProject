package models;
import interfaces.CConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ShippingAddress {
private String address;
private String city;
private String country;
private int zipCode;
private CConnection cConnection;

public ShippingAddress(String id, CConnection c){
 String sql = "SELECT * FROM address WHERE customer_id='" + id +
"'";
 Statement stmt;
 try {
 stmt = c.cn.createStatement();
 ResultSet rs = stmt.executeQuery(sql);
 while(rs.next()){
 this.setAddress(rs.getString("address"));
this.setCity(rs.getString("city"));
this.setCountry(rs.getString("country"));
this.setZipCode(rs.getInt("zip_code"));
 }
 } catch (SQLException ex) {
 ex.printStackTrace();
 }

}
public void finalize() throws Throwable {
}
public String getAddress(){
return address;
}
public String getCity(){
return city;
}
public String getCountry(){
return country;
}
public int getZipCode(){
return zipCode;
}
public void setAddress(String newVal){
address = newVal;
}
public void setCity(String newVal){
city = newVal;
}
public void setCountry(String newVal){
country = newVal;
}
public void setZipCode(int newVal){
zipCode = newVal;
}
public CConnection getCConnection(){
return cConnection;
}
public void setCConnection(CConnection newVal){
cConnection = newVal;
}


}//end ShippingAddress