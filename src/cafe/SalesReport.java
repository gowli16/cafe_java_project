package cafe;
import java.util.*;
import java.io.*;

public class SalesReport {

    String pathcart="cart.csv";
    String pathsales="salesreport.csv";
    private int reportId;
    private double totalSales;
    private String bestSellingItem;
    private Date reportDate;

    public SalesReport(int reportId, double totalSales, String bestSellingItem, Date reportDate) {
        this.reportId = reportId;
        this.totalSales = totalSales;
        this.bestSellingItem = bestSellingItem;
        this.reportDate = reportDate;
    }

    public void generateDailyReport() {
        ArrayList<String> itemNames = new ArrayList<String>();
        totalSales = 0;
        bestSellingItem = "";
        reportDate = new Date();

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathcart));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                itemNames.add(data[1]);
                totalSales = totalSales + Double.parseDouble(data[2]);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error reading cart file");
        }

        int maxCount = 0;

        for (int i = 0; i < itemNames.size(); i++) {
            int count = 0;

            for (int j = 0; j < itemNames.size(); j++) {
                if (itemNames.get(i).equalsIgnoreCase(itemNames.get(j))) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                bestSellingItem = itemNames.get(i);
            }
        }

        reportId = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathsales));
            while (br.readLine() != null) {
                reportId++;
            }
            br.close();
        } catch (IOException e) {
            reportId = 1;
        }

        try {
            File file = new File(pathsales);
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathsales, true));

            if (file.length() == 0) {
                bw.write("reportId,totalSales,bestSellingItem,reportDate");
                bw.newLine();
            }

            bw.write(reportId + "," + totalSales + "," + bestSellingItem + "," + reportDate);
            bw.newLine();

            bw.close();
            System.out.println("Sales report generated successfully");
        } catch (IOException e) {
            System.out.println("Error writing sales report");
        }
    }

    public String getBestSellingItems() {
        return bestSellingItem;
    }

    public void displayReport() {
        generateDailyReport();
        System.out.println("Report ID: " + reportId);
        System.out.println("Total Sales: " + totalSales);
        System.out.println("Best Selling Item: " + bestSellingItem);
        System.out.println("Report Date: " + reportDate);
    }

   /* public static void main(String[] args) {
        SalesReport report = new SalesReport(0, 0, "", null);
        report.generateDailyReport();
        report.displayReport();
    }*/
}
