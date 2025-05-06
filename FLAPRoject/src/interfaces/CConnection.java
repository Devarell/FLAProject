package interfaces;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class CConnection {
public Connection cn;
static final String DATABASE_URL =
"jdbc:mysql://localhost/sales_system";
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
public CConnection(){
}
public void finalize() throws Throwable {
}

public void closeConnection(){
 try{
 if(!cn.isClosed()){
	 cn.close();
	 System.out.println("Database closed!");
	  }
	  }catch(SQLException ex){
	  ex.printStackTrace();
	  }
	 }
	 public void openConnection(){
	  try{
	  Class.forName(this.JDBC_DRIVER);
	  }catch(ClassNotFoundException ex){
	  ex.printStackTrace();
	  }
	  try{
	  cn =
	 DriverManager.getConnection(this.DATABASE_URL,"root","");
	  System.out.println("Database Connected!");
	  }catch(SQLException ex){
	  ex.printStackTrace();
	  }
	 }
	 }//end CConnection
 