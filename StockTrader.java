







// 1. i would say that the dip buyer is the best in general with lots of wins and very few losses
//2.  it didnt do well in falling.txt it kept buying and buying, but the stock never recovered.
//3. in appl_2000_2010.txt apple barely hade manyb dips, while maintaining a strong consistant growth with lots oof informations. when there is a lot of info, the moving average thrives.
//4.  my strategy is pretty reilient because its just moving average with a random aspect to it in order to muffle wins and losses. if i changed it, on some it will probably do worse on others because each stock has unique movements. 











import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class StockTrader {
public static final int whichOne = 1;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("sp500_1990_2025.txt"));
        String symbol = in.next();
        int n = in.nextInt();
        
        int[] years = new int[n];
        int[] months = new int[n];
        double[]prices = new double[n];
        loadData(in,years,months,prices);

        // TODO: create the three arrays, each with n elements

        // TODO: call loadData, then printData

        printData(years, months, prices,n);
        int low = indexOfLowest(prices);
        int high = indexOfHighest(prices);
        
        System.out.println(symbol + " lowest:  $" + prices[low]+" in " + monthName(months[low]) +" " + years[low]);
        System.out.println(symbol + " highest:  $" + prices[high]+" in " + monthName(months[high]) +" " + years[high]);
        
        double result = simulate(years, months, prices);
System.out.printf("Final value: $%,.2f%n", result);
    }

public static final double START_CASH = 10000.0;
public static final double FEE = 10.0;

public static double simulate(int[] years, int[] months, double[] prices) {
    double cash = START_CASH;
    int shares = 0;
    double boughtAt = 0.0;

    for (int i = 0; i < prices.length; i++) {
        double[] history = Arrays.copyOf(prices, i + 1);
        String action = decide(history, shares > 0, boughtAt);

        if (action.equals("BUY") && shares == 0) {
            int canBuy = (int) ((cash - FEE) / prices[i]);
            if (canBuy > 0) {
                shares = canBuy;
                cash = cash - shares * prices[i] - FEE;
                boughtAt = prices[i];
                System.out.printf("%s %d: BUY %d shares at $%.2f%n",
                        monthName(months[i]), years[i], shares, prices[i]);
            }
        } else if (action.equals("SELL") && shares > 0) {
            cash = cash + shares * prices[i] - FEE;
            System.out.printf("%s %d: SELL %d shares at $%.2f%n",
                    monthName(months[i]), years[i], shares, prices[i]);
            shares = 0;
            boughtAt = 0.0;
        }
    }
    return cash + shares * prices[prices.length - 1];
}












public static String decide(double[] history, boolean holding, double boughtAt) {
	if (whichOne == 2){
    return dipBuyer(history,holding,boughtAt);
 }else if(whichOne == 1){
  return buyAndHold(history,holding,boughtAt);
}else if(whichOne == 3){
 return movingAverage(history,holding,boughtAt);
} else{
  return myStrategy(history,holding,boughtAt);
}
}




public static String dipBuyer( double[] history, boolean holding, double boughtAt){
	int len =history.length-1 ;

	if(len<2){return "HOLD";}	

	if(history[len]<history[len-1]){
		if(history[len-1]<history[len-2]){
		return "BUY";
	}	
}
	if(boughtAt * 1.1<=history[len]){
		return "SELL";
	}	

return"HOLD";
}
public static String buyAndHold( double[] history, boolean holding, double boughtAt){
return"BUY";
}
public static String movingAverage( double[] history, boolean holding, double boughtAt){
	int len =history.length-1 ;

	if(len<11){return "HOLD";}	
	double avg = (history[len] +history[len-1] + history[len-2]+ history[len-3] + history[len-4] + history[len-5] + history[len-6] + history[len-7] + history[len-8] + history[len-9] + history[len-10] + history[len-11])/12;

	if(history[len]> avg && holding == false){

		return "BUY";
	
}
	if(history[len]< avg && holding == true){

		return "SELL";
	
}	

return"HOLD";
}
public static String myStrategy( double[] history, boolean holding, double boughtAt){
	int len =history.length-1 ;
	
	Random rand = new Random();
	double num = rand.nextDouble(); 


	if(len<11){return "HOLD";}	
	double avg = (history[len] +history[len-1] + history[len-2]+ history[len-3] + history[len-4] + history[len-5] + history[len-6] + history[len-7] + history[len-8] + history[len-9] + history[len-10] + history[len-11])/12;

	if(history[len]> avg && holding == false){

		return "BUY";
	
}
	if(history[len]< avg && holding == true){

		return "SELL";
	
}	



if(num>.5){
	return "BUY";
}	

if(num<.5){
	return "SELL";
}	

return"HOLD";
}







public static void loadData(Scanner in, int[] years, int[] months, double[] prices){
 for(int i = 0; i<years.length; i++){
 years[i] = in.nextInt();
 months[i] = in.nextInt();  
 prices[i] = in.nextDouble();
 

 
}
 

}
public static int indexOfLowest(double[] prices){
	double lowest = 100000;
	int tracker = 0;
	for(int i = 0; i<prices.length;i++){
	if (prices[i]<lowest){
	lowest = prices[i];
	tracker = i;	
	}
		
	}

 return tracker;
}

public static int indexOfHighest(double[] prices){
	double hi = 0;
		int tracker = 0;
	for(int i = 0; i<prices.length;i++){
	if (prices[i]>hi){
	hi = prices[i];
	tracker = i;	
	}
		
	}

 return tracker;
}
 




public static String monthName(int month){
String[] dates = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
String monthw = dates[month-1];


return monthw;

}


public static void printData(int[] years, int[] months, double[] prices, int num){
 for(int i = 0; i<num; i++){
	 System.out.print(monthName(months[i]));
	 System.out.print(" "+ years[i]+": ");	 
	 System.out.println(" $"+ prices[i]);	 	 
 }
}
}
