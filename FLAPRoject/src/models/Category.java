package models;
import interfaces.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
public class Category implements ICrud {
private String categoryName;
private String categoryId;
private CConnection cConnection;
public Category(String id, CConnection c){
 String sql = "SELECT * FROM category WHERE category_id='" + id
+ "'";
 Statement stmt;
 try {
 stmt = c.cn.createStatement();
 ResultSet rs = stmt.executeQuery(sql);
 while(rs.next()){
 this.setCategoryId(id);
this.setCategoryName(rs.getString("category_name"));
 }
 } catch (SQLException ex) {

Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
 }
}
public void finalize() throws Throwable {
}
public void create(){
 String sql = "INSERT INTO category VALUES('" +
 this.getCategoryId() + "','" +
this.getCategoryName() + "')";
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
  String sql = "DELETE FROM category WHERE category_id='" +
  this.getCategoryId() + "'";
  try {
  Statement stmt =
 this.getCConnection().cn.createStatement();
  stmt.executeUpdate(sql);
  } catch (SQLException ex) {
  Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
 null, ex);
  }
 }
 public String getCategoryName(){
 return categoryName;
 }
 public List<Category> read(){
  String sql = "SELECT * FROM category" ;
  ResultSet rs = null;
  List<Category> categories = new ArrayList<Category>();

  try {
  Statement stmt =
 this.getCConnection().cn.createStatement();
  rs = stmt.executeQuery(sql);
  while(rs.next()){
  Category c = new
 Category(rs.getString("category_id"),this.getCConnection());
  categories.add(c);
  }
  } catch (SQLException ex) {
  Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
 null, ex);
  }
  return categories;
 }
 public void setCategoryName(String newVal){
 categoryName = newVal;
 }
 public void update(){
  String sql = "UPDATE category SET categery_name'" +
  this.getCategoryName() +
 "' WHERE category_id='" + this.getCategoryId() + "'" ;
  try {
	  Statement stmt =
	 this.getCConnection().cn.createStatement();
	  stmt.executeUpdate(sql);
	  } catch (SQLException ex) {
	  Logger.getLogger(Product.class.getName()).log(Level.SEVERE,
	 null, ex);
	  }
	 }
	 public String getCategoryId(){
	 return categoryId;
	 }
	 public void setCategoryId(String newVal){
	 categoryId = newVal;
	 }
	 public CConnection getCConnection(){
	 return cConnection;
	 }
	 public void setCConnection(CConnection newVal){
	 cConnection = newVal;
	 }
	 }//end Category