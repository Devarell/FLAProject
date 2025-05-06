package models;
import interfaces.CConnection;
import interfaces.ICrud;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
public class Product implements ICrud {
private Category category;
private Discount discount;
private double price;
private String productName;
private String productId;
private CConnection cConnection;

public Product(String id, CConnection c){
 //cari data product berdasarkan id
 if(c != null){
 ResultSet rs=null;
 try{
 //setting object product
String sql="SELECT * FROM product WHERE product_id='"
+ id + "'";
 Statement stmt =
c.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
 ResultSet.CONCUR_READ_ONLY);
 rs = stmt.executeQuery(sql);
String cat = "";
while(rs.next()){
 this.setProductId(rs.getString("product_id"));
this.setProductName(rs.getString("product_name"));
this.setPrice(rs.getDouble("price"));
cat = rs.getString("category_id");
 }
 //setting object category
category = new Category(id,c);
this.setCategory(category);
 //setting object discount
discount = new Discount(id,c);
this.setDiscount(discount);
 }catch(SQLException e){
 System.out.println(e.getMessage());
 }
 }
}
public void finalize() throws Throwable {
 }
public void create(){
 String sql = "INSERT INTO product VALUES('" +
 this.getProductId() + "','" +
this.getProductName() + "','" +
this.getPrice() + "','" +
this.getCategory().getCategoryId() + "')";
 try {
 Statement stmt =
this.getCConnection().cn.createStatement();
 stmt.executeUpdate(sql);
 } catch (SQLException ex) {
 Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
null, ex);
 }
}
public void delete(){
	 String sql = "DELETE FROM product WHERE product_id='" +
	this.getProductId() + "'";

	 try {
	 Statement stmt =
	this.getCConnection().cn.createStatement();
	 stmt.executeUpdate(sql);
	 } catch (SQLException ex) {
	 Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
	null, ex);
	 }
	}
	public List<Product> findByCategory(){
	 String sql = "SELECT * FROM product WHERE category_id='" +
	 this.getCategory().getCategoryId() + "'" ;
	 ResultSet rs = null;
	 List<Product> products = new ArrayList<Product>();

	 try {
	 Statement stmt =
	this.getCConnection().cn.createStatement();
	 rs = stmt.executeQuery(sql);
	 while(rs.next()){
	 Product p = new
	Product(rs.getString("product_id"),this.getCConnection());
	 products.add(p);
	 }
	 } catch (SQLException ex) {
	 Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
	null, ex);
	 }
	 return products;
	}
	public Category getCategory(){
	return category;
	}
	public Discount getDiscount(){
	return discount;
	}
	public double getPrice(){
	return price;
	}
	public String getProductName(){
	return productName;
	}
	public List<Product> read(){
	 String sql = "SELECT * FROM product" ;
	 ResultSet rs = null;
	 List<Product> products = new ArrayList<Product>();

	 try {
	 Statement stmt =
	this.getCConnection().cn.createStatement();
	 rs = stmt.executeQuery(sql);
	 while(rs.next()){
	 Product p = new
	Product(rs.getString("product_id"),this.getCConnection());
	 products.add(p);
	 }
	 } catch (SQLException ex) {
	 Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
	null, ex);
	 }
	 return products;
	}
	public void setCategory(Category newVal){
	category = newVal;
	}
	public void setDiscount(Discount newVal){
	discount = newVal;
	}
	public void setPrice(double newVal){
	price = newVal;
	}
	public void setProductName(String newVal){
		productName = newVal;
	}
	public void update(){
	 String sql = "UPDATE product SET product_name'" +
	this.getProductName()
	 + "',price='" + this.getPrice()
	+ "',category_id='" +
	this.getCategory().getCategoryId()
	 + "' WHERE product_id='" + this.getProductId() + "'";
	 try {
	 Statement stmt =
	this.getCConnection().cn.createStatement();
	 stmt.executeUpdate(sql);
	 } catch (SQLException ex) {
	 Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
	null, ex);
	 }
	}
	public String getProductId(){
	return productId;
	}
	public void setProductId(String newVal){
	productId = newVal;
	}
	public CConnection getCConnection(){
	return cConnection;
	}
	public void setCConnection(CConnection newVal){
	cConnection = newVal;
	}
	}//end Product
