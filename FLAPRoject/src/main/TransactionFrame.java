package main;
//TransactionFrame.java - Form Transaksi Penjualan UI (Simplified)

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TransactionFrame extends JFrame {
 private JTextField invoiceNumber, date, customerName, customerId,
         address, country, city, zipCode, productId, productName, price,
         qty, discount, total, totalDiscount, netTotal;
 private JComboBox<String> orderType;
 private JTable itemDetail;
 private DefaultTableModel defItem;
 private JButton newOrder, save, savePrint;

 private int noUrut = 1;
 private double grossTotal = 0.0;
 private double discountAmount = 0.0;

 public TransactionFrame() {
     initComponents();
     newOrder();
 }

 private void initComponents() {
     setTitle("Transaksi Penjualan");
     setSize(800, 600);
     setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     setLayout(new BorderLayout());

     // Panel Info Invoice
     JPanel invoicePanel = new JPanel(new GridLayout(6, 2));
     invoicePanel.setBorder(BorderFactory.createTitledBorder("Invoice Information"));

     invoiceNumber = new JTextField();
     date = new JTextField();
     orderType = new JComboBox<>(new String[]{"-Choose-", "Partial", "Grosser"});
     customerName = new JTextField();
     customerId = new JTextField();
     address = new JTextField();
     country = new JTextField();
     city = new JTextField();
     zipCode = new JTextField();

     invoicePanel.add(new JLabel("Invoice Number")); invoicePanel.add(invoiceNumber);
     invoicePanel.add(new JLabel("Date")); invoicePanel.add(date);
     invoicePanel.add(new JLabel("Order Type")); invoicePanel.add(orderType);
     invoicePanel.add(new JLabel("Customer Name")); invoicePanel.add(customerName);
     invoicePanel.add(new JLabel("Customer ID")); invoicePanel.add(customerId);
     invoicePanel.add(new JLabel("Address")); invoicePanel.add(address);

     // Panel Produk
     JPanel productPanel = new JPanel(new GridLayout(3, 2));
     productPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
     productId = new JTextField();
     productName = new JTextField();
     price = new JTextField();
     qty = new JTextField();
     discount = new JTextField();

     productPanel.add(new JLabel("Product ID")); productPanel.add(productId);
     productPanel.add(new JLabel("Product Name")); productPanel.add(productName);
     productPanel.add(new JLabel("Price")); productPanel.add(price);

     // Table Detail Item
     defItem = new DefaultTableModel(new String[]{"No", "Product", "Price", "Qty", "Discount", "Subtotal"}, 0);
     itemDetail = new JTable(defItem);

     // Total Panel
     JPanel totalPanel = new JPanel(new GridLayout(3, 2));
     total = new JTextField();
     totalDiscount = new JTextField();
     netTotal = new JTextField();
     totalPanel.add(new JLabel("Total")); totalPanel.add(total);
     totalPanel.add(new JLabel("Total Discount")); totalPanel.add(totalDiscount);
     totalPanel.add(new JLabel("Net Total")); totalPanel.add(netTotal);

     // Button Panel
     JPanel buttonPanel = new JPanel();
     newOrder = new JButton("New Order");
     save = new JButton("Save");
     savePrint = new JButton("Save & Print");
     buttonPanel.add(newOrder);
     buttonPanel.add(save);
     buttonPanel.add(savePrint);

     // Add everything
     JPanel topPanel = new JPanel(new GridLayout(2, 1));
     topPanel.add(invoicePanel);
     topPanel.add(productPanel);
     add(topPanel, BorderLayout.NORTH);
     add(new JScrollPane(itemDetail), BorderLayout.CENTER);
     add(totalPanel, BorderLayout.EAST);
     add(buttonPanel, BorderLayout.SOUTH);

     // Button actions (placeholders)
     newOrder.addActionListener(e -> newOrder());
     save.addActionListener(e -> JOptionPane.showMessageDialog(this, "Order Saved"));
     savePrint.addActionListener(e -> JOptionPane.showMessageDialog(this, "Order Saved & Printed"));
 }

 private void newOrder() {
     invoiceNumber.setText("INV" + new Random().nextInt(999));
     date.setText(String.format("%tF", Calendar.getInstance()));
     customerName.setText("");
     customerId.setText("");
     address.setText("");
     country.setText("");
     city.setText("");
     zipCode.setText("");
     productId.setText("");
     productName.setText("");
     price.setText("");
     qty.setText("");
     discount.setText("");
     defItem.setRowCount(0);
     grossTotal = 0.0;
     discountAmount = 0.0;
     noUrut = 1;
 }

 // Static method to launch the TransactionFrame
 public static void showTransactionForm() {
     SwingUtilities.invokeLater(() -> {
         TransactionFrame tf = new TransactionFrame();
         tf.setLocationRelativeTo(null);
         tf.setVisible(true);
     });
 }
}