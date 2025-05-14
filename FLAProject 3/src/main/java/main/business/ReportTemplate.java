package main.business;

public abstract class ReportTemplate {
    
    public final void generateReport() {
        printHeader();
        calculateData();
        displayResults();
        printFooter();
    }
    
    protected void printHeader() {
        System.out.println("\n=== Laporan Roemah Soto ===");
        System.out.println("Tanggal: " + java.time.LocalDate.now());
        System.out.println("============================");
    }
    
    protected abstract void calculateData();
    
    protected abstract void displayResults();
    
    protected void printFooter() {
        System.out.println("============================");
        System.out.println("*** Akhir Laporan ***\n");
    }
}
