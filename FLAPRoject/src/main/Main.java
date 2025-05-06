package main;

public class Main {
    public static void main(String[] args) {
        // Always run Swing UI on Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            BrowseTransactionFrame frame = new BrowseTransactionFrame();
            frame.setLocationRelativeTo(null); // center the frame on screen
            frame.setVisible(true);            // make it visible
        });
    }
}
