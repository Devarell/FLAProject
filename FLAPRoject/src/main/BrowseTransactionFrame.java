package main;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BrowseTransactionFrame extends JFrame {
    private JComboBox<String> searchBy;
    private JTextField search;
    private JTable tableList;
    private JButton go, addNew, delete, close;

    public BrowseTransactionFrame() {
        initComponents();
        fillTableList(false); // populate table
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Browse Transactions");

        searchBy = new JComboBox<>(new String[]{"-Choose-", "Invoice", "Customer", "Date"});
        search = new JTextField(15);
        tableList = new JTable();
        go = new JButton("Go");
        addNew = new JButton("Add New");
        delete = new JButton("Delete");
        close = new JButton("Close");

        // Add layout (use your preferred layout manager)
        JPanel panel = new JPanel();
        panel.add(searchBy);
        panel.add(search);
        panel.add(go);
        panel.add(addNew);
        panel.add(delete);
        panel.add(close);

        add(panel, "North");
        add(new JScrollPane(tableList), "Center");

        // Events
        go.addActionListener(e -> fillTableList(true));
        close.addActionListener(e -> dispose());

        // Dummy function placeholder (implement based on your logic)
        addNew.addActionListener(e -> {
            TransactionFrame tf = new TransactionFrame();
            tf.setLocationRelativeTo(null);
            tf.setVisible(true);
        });

        // Resize the window
        setSize(800, 600);
    }

    // Example placeholder method
    private void fillTableList(boolean isFiltered) {
        // implement based on your logic class
        // e.g., connect to DB and populate tableList using DefaultTableModel
        System.out.println("Fill table here (filtered = " + isFiltered + ")");
    }
}
