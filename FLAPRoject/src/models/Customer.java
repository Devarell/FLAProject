package models;
import interfaces.CConnection;
import interfaces.ICrud;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Customer implements ICrud {
private String customerName;
private Date dateOfBirth;
private String gender;
private ShippingAddress shippingAddress;
private String customerId;
private CConnection cConnection;
public Customer(String id, CConnection c){

 String sql = "SELECT * FROM customer WHERE customer_id='" + id
+ "'";
 Statement stmt;
 ResultSet rs = null;
 if(c != null){
 try {
 stmt = c.cn.createStatement();
rs = stmt.executeQuery(sql);
while(rs.next()){
 this.setCustomerId(id);
this.setCustomerName(rs.getString("customer_name"));
 this.setGender(rs.getString("gender"));
this.setDateOfBirth(rs.getDate("date_of_birth"));
 }
//setting object ShippingAddress
ShippingAddress sa = new ShippingAddress(id,c);
this.setshippingAddress(sa);
 } catch (SQLException ex) {

Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
 }
 }

}
public void finalize() throws Throwable {
}
public void create(){
	 String sql;
	 sql="INSERT INTO customer VALUES('" +
	 this.getCustomerId() + "','" +
	this.getCustomerName() + "','" +
	this.getGender() + "','" +
	this.getDateOfBirth() + "')" ;
	 try{
	 Statement stmt = cConnection.cn.createStatement();
	 cConnection.cn.setAutoCommit(false);
	 stmt.executeUpdate(sql);

	 sql = "INSERT INTO address VALUES('0'," +
	 this.getCustomerId() + "','" +
	this.getshippingAddress().getAddress() + "','" +
	this.getshippingAddress().getZipCode() + "','" +
	this.getshippingAddress().getCity() + "','" +
	this.getshippingAddress().getCountry() + "')";
	 stmt.executeUpdate(sql);
	 cConnection.cn.commit();
	 }catch(SQLException e){
	 try {
	 cConnection.cn.rollback();
	 } catch (SQLException ex) {

	Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null,
	ex);
	 }
	Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null,
	e);
	 }
	}
	public void delete(){
	 String sql;
	 sql="DELETE FROM customer WHERE customer_id='" +
	this.getCustomerId() + "'";
	 try{
	 Statement stmt = cConnection.cn.createStatement();
	 stmt.executeUpdate(sql);
	 }catch(SQLException e){

	Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, e);
	 }
	}
	public List<Customer> findByName(String name){
	 String sql = "SELECT * FROM customer WHERE customer_name LIKE'"
	+ name + "%'";
	 List<Customer> customers = new ArrayList<Customer>();
	 Statement stmt;
	 ResultSet rs = null;
	 try {
	 stmt = cConnection.cn.createStatement();
	 rs = stmt.executeQuery(sql);
	 //String id = "";
	 while(rs.next()){
	 Customer c = new
	Customer(rs.getString("customer_id"),this.getCConnection());
	 customers.add(c);
	 }

	 } catch (SQLException ex) {

	Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
	 }
	return customers;
	}
	public Customer getCustomer(String name){
	return null;
	}
	public String getCustomerName(){
		return customerName;
	}
	public Date getDateOfBirth(){
	return dateOfBirth;
	}
	public String getGender(){
	return gender;
	}
	public ShippingAddress getshippingAddress(){
	return shippingAddress;
	}


	public List<Customer> read(){
	 List<Customer> customers = new ArrayList<Customer>();
	 ResultSet rs=null;

	 try{
	 //setting object product
	 String sql="SELECT * FROM customer";
	 Statement stmt =
	cConnection.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 ResultSet.CONCUR_READ_ONLY);
	 rs = stmt.executeQuery(sql);
	 String cat = "";
	 while(rs.next()){
	 Customer aCustomer = new
	Customer(rs.getString("customer_id"),this.getCConnection());
	 customers.add(aCustomer);
	 }

	 }catch(SQLException e){
	 System.out.println(e.getMessage());
	 }
	 return customers;
	 }
	public void setCustomerName(String newVal){
		customerName = newVal;
		}
		public void setDateOfBirth(Date newVal){
		dateOfBirth = newVal;
		}
		public void setGender(String newVal){
		gender = newVal;
		}
		public void setshippingAddress(ShippingAddress newVal){
		shippingAddress = newVal;
		}
		public void update(){
		 String sql;
		 sql="UPDATE customer SET customer_name ='" +
		this.getCustomerName() 
		 + "',gender='" + this.getGender()
		 + "',date_of_birth='" + this.getDateOfBirth()
		 + "' WHERE customer_id ='" + this.getCustomerId() + "'"
		 ;
		  try{
		  Statement stmt = cConnection.cn.createStatement();
		  stmt.executeUpdate(sql);
		  }catch(SQLException e){
		  e.printStackTrace();
		  }
		 }
		 public String getCustomerId(){
		 return customerId;
		 }
		 public void setCustomerId(String newVal){
		 customerId = newVal;
		 }
		 public CConnection getCConnection(){
		 return cConnection;
		 }
		 public void setCConnection(CConnection newVal){
		 cConnection = newVal;
		 }
		 }//end Customer