package models;
import interfaces.CConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Discount {
private float discountPercentage;
public Discount(String id, CConnection c){
 String sql = "SELECT * FROM discount WHERE product_id='" + id +
"'";
 Statement stmt;
 try {
 stmt = c.cn.createStatement();
 ResultSet rs = stmt.executeQuery(sql);
 if(rs.next()){

this.setDiscountPercentage(rs.getFloat("discount_percentage"));
 }
 } catch (SQLException ex) {
 ex.printStackTrace();
 }

}
public void finalize() throws Throwable {
}
public float getDiscountPercentage(){
return discountPercentage;
}
public void setDiscountPercentage(float newVal){
discountPercentage = newVal;
}
}//end Discount
