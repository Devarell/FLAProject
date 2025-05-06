package models;
import interfaces.CConnection;
import interfaces.ICrud;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.*;
public class CustomerOrder implements ICrud {
private Customer customer;
private String invoiceNumber;
private List<LineItem> lineItems = new ArrayList<LineItem>();
private Date orderDate;
private double total;
private CConnection cConnection;
public CustomerOrder(){
}
public void finalize()
 throws Throwable{
}
public void addLineItem(List<LineItem> newLineItem){
 this.lineItems = newLineItem;
}
public void create(){
 System.out.println("Create method from class CustomerOrder!");
}
public String getInvoiceNumber(){
return invoiceNumber;
}
public Date getOrderDate(){
return orderDate;
}
public double getTotal(){
return total;
}
public List<CustomerOrder> read(){
 List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
 String sql = "SELECT * FROM orders";// ORDER BY order_date";
 ResultSet rs = null;
 ResultSet rsItem = null;

 try {
 Statement stmt =
cConnection.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
 ResultSet.CONCUR_READ_ONLY);
 rs = stmt.executeQuery(sql);

 while(rs.next()){
 Statement st = cConnection.cn.createStatement();
 CustomerOrder co = new CustomerOrder();
 co.setInvoiceNumber(rs.getString("order_id"));
 co.setOrderDate(rs.getDate("order_date"));
 co.setTotal(rs.getDouble("total"));
 Customer cust = new
 Customer(rs.getString("customer_id"),cConnection);
  co.setCustomer(cust);
 sql = "SELECT * FROM order_items WHERE order_id ='" +
 co.getInvoiceNumber() + "'";
  rsItem = st.executeQuery(sql);
  while(rsItem.next()){
  List<LineItem> listItem = new
 ArrayList<LineItem>();
  LineItem item = new LineItem();
 item.setDiscount(rsItem.getDouble("discount"));
 item.setQty(rsItem.getInt("qty"));
 Product p = new
 Product(rsItem.getString("product_id"),cConnection);
  item.setProduct(p);
 listItem.add(item);
 co.addLineItem(listItem);
  }


  orders.add(co);
  }
  } catch (SQLException ex) {

 Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null,
 ex);
  }

  return orders;
 }

  public List<CustomerOrder> getFilteredOrder(String fieldName,
 String fieldValue){
  List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
  String sql = "SELECT * FROM orders " +
  "INNER JOIN customer ON orders.customer_id = customer.customer_id " + "WHERE " + fieldName + " LIKE'%" + fieldValue + "%'";
  ResultSet rs = null;
  ResultSet rsItem = null;

  try {
  Statement stmt =
 cConnection.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
  ResultSet.CONCUR_READ_ONLY);
  rs = stmt.executeQuery(sql);

  while(rs.next()){
  Statement st = cConnection.cn.createStatement();
 CustomerOrder co = new CustomerOrder();
 co.setInvoiceNumber(rs.getString("order_id"));
 co.setOrderDate(rs.getDate("order_date"));
 co.setTotal(rs.getDouble("total"));
 Customer cust = new
 Customer(rs.getString("customer_id"),cConnection);
 co.setCustomer(cust);
 sql = "SELECT * FROM order_items WHERE order_id ='" +
 co.getInvoiceNumber() + "'";
  rsItem = st.executeQuery(sql);
  while(rsItem.next()){
  List<LineItem> listItem = new
 ArrayList<LineItem>();
  LineItem item = new LineItem();
 item.setDiscount(rsItem.getDouble("discount"));
 item.setQty(rsItem.getInt("qty"));
 Product p = new
 Product(rsItem.getString("product_id"),cConnection);
  item.setProduct(p);
 listItem.add(item);
 co.addLineItem(listItem);
  }


  orders.add(co);
  }
  } catch (SQLException ex) {

 Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null,
 ex);
  }

  return orders;
  }
 public void setInvoiceNumber(String newVal){
 invoiceNumber = newVal;
 }
 public void setOrderDate(Date newVal){
 orderDate = newVal;
 }
 public void setTotal(double newVal){
 total = newVal;
 }
 public void update(){
  //no update method
 }
 public void delete(){

  String sql;


  try{
  Statement stmt =
 this.getCConnection().cn.createStatement();
  sql="DELETE FROM orders WHERE order_id='" +
 this.getInvoiceNumber() + "'";
  stmt.executeUpdate(sql);
  sql = "DELETE FROM order_items WHERE order_id='" +
 this.getInvoiceNumber() + "'";
  stmt.executeUpdate(sql);
  }catch(SQLException e){
	  System.out.println(e.getMessage());

	  }

	 }
	 public Customer getCustomer(){
	 return customer;
	 }
	 public void setCustomer(Customer newVal){
	 customer = newVal;
	 }
	 public CConnection getCConnection(){
	 return cConnection;
	 }
	 public void setCConnection(CConnection newVal){
	 cConnection = newVal;
	 }
	 public List<LineItem> getLineItem(){
	 return this.lineItems;
	 }
	 public String getNewInvoiceNumber(){
	  ResultSet rs = null;
	  Calendar cl = Calendar.getInstance();
	  String tempNumber = "F" + String.format("%td", cl) +
	 String.format("%tm", cl) + String.format("%ty", cl);
	  try {
	  Statement stmt = cConnection.cn.createStatement();
	  String sql = "SELECT * FROM orders WHERE order_id LIKE '" +
	 tempNumber + "%' ORDER BY order_id";
	  rs = stmt.executeQuery(sql);
	  String tempString = null;
	  while(rs.next()){
	  tempString = rs.getString("order_id");
	  }
	  //nomor otomatis
	  if(tempString != null){
	  String noUrut = tempString.substring(7);
	  int nextNoUrut = Integer.parseInt(noUrut);
	  nextNoUrut = nextNoUrut + 1;
	  noUrut = String.valueOf(nextNoUrut);
	  if(noUrut.length() == 1){
	   noUrut = "00" + noUrut;
	   }else if(noUrut.length() == 2){
	   noUrut = "0" + noUrut;
	   }
	  tempNumber = tempNumber + noUrut;
	   }else{
	   tempNumber = tempNumber + "001";
	   }
	  } catch (SQLException ex) {

		  Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null,
		  ex);
		   }

		   return tempNumber;
		  }
		  }//end CustomerOrder