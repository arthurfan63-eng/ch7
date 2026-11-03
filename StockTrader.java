import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();
        
        int[] years = new int[n];
        int[] months = new int[n];
        double[]prices = new double[n];
        loadData(in,years,months,prices);

        // TODO: create the three arrays, each with n elements

        // TODO: call loadData, then printData
        loadData(in,years,months,prices);
    }



public static void loadData(Scanner in, int[] years, int[] months, double[] prices){
 for(int i = 0; i<years.length; i++){
 years[i] = in.nextInt();
 months[i] = in.nextInt();  
 prices[i] = in.nextDouble();
 
}
 
 
}



public static String monthName(int month){
String[] dates = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
String monthw = dates[month-1];


return monthw;

}


public static void printData(int[] years, int[] months, double[] prices){
 for(int i = 0; i<12; i++){
	 System.out.print(monthName(months[i]));
	 System.out.print(" "+ years[i]+": ");	 
	 System.out.println(" "+ prices[i]);	 	 
 }
}
}
