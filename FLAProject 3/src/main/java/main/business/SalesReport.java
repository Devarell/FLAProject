package main.business;

import main.database.OrderDatabase;

public class SalesReport extends ReportTemplate {
    private double totalSales = 0;
    private int totalOrders = 0;
    
    @Override
    protected void calculateData() {
        OrderDatabase db = OrderDatabase.getInstance();
        totalOrders = db.getOrders().size();
        totalSales = db.calculateMonthlyRevenue();
    }
    
    @Override
    protected void displayResults() {
        System.out.println("Total Pesanan: " + totalOrders);
        System.out.println("Total Pendapatan: Rp." + totalSales);
        System.out.println("Rata-rata per Pesanan: Rp." + 
            (totalOrders > 0 ? totalSales/totalOrders : 0));
    }
}
