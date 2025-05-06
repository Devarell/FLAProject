package models;
import interfaces.CConnection;
import java.sql.*;
import java.util.Iterator;
public class PartialOrder extends CustomerOrder {
public PartialOrder(){
}
public void finalize() throws Throwable {
super.finalize();
}
public void create(){

 try {
 //start trasaction
 this.getCConnection().cn.setAutoCommit(false);

 String sql = "INSERT INTO orders VALUES('" +
 this.getInvoiceNumber() + "','" +
this.getOrderDate() + "','" +
this.getCustomer().getCustomerId() + "','" +
this.getTotal() + "','" +
"Partial Order" + "')";

 Statement stmt =
this.getCConnection().cn.createStatement();
 //execute query
 stmt.executeUpdate(sql);

 for(Iterator i = this.getLineItem().iterator(); 
		 i.hasNext();){
	 LineItem item = (LineItem) i.next();
	String sql2 = "INSERT INTO order_items VALUES('" +
	 this.getInvoiceNumber() + "','" +
	item.getProduct().getProductId() + "','" +
	item.getQty() + "','" +
	item.getDiscount() + "')";
	 //execute query
	stmt.executeUpdate(sql2);
	 }
//commit all queries
this.getCConnection().cn.commit();
System.out.println("Data tersimpan!");

} catch (SQLException ex) {
if(this.getCConnection().cn != null){
try {
//jika terjadi error rollback transaction
this.getCConnection().cn.rollback();
} catch (SQLException ex1) {
ex1.printStackTrace();
}
}
ex.printStackTrace();
}

}
}//end PartialOrder