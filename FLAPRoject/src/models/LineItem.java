package models;
public class LineItem {
private double discount;
private Product product;
private int qty;
public LineItem(){
}
public void finalize() throws Throwable {
}
public double getDiscount(){
return discount;
}
public Product getProduct(){
return product;
}
public int getQty(){
return qty;
}
public void setDiscount(double newVal){
discount = newVal;
}
public void setProduct(Product newVal){
product = newVal;
}
public void setQty(int newVal){
qty = newVal;
}
}//end LineItem