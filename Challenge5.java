/*
Programming Challenge Description:
We will now alter the benchmark string so that it includes a price field.

An example benchmark holding will look like this, where 15 is the quantity and 10 is the price.

[VOD,Vodafone,15,10]
The value of a holding in a portfolio will be its price multiplied by quantity

The Net Asset Value of a portfolio (NAV for short) is the total value of the portfolio, in this case the sum of each holding's value.

The Percentage Net Asset Value of a holding is the holding's value as a percentage of the portfolio NAV. This is calculated as:

(Holding value / Portfolio value) * 100
For example:

Ticker Name      Quantity Price  Value  %NAV
VOD    Vodafone  10       16     160    36.45
GOOG   Google    15       9      135    30.75
MSFT   Microsoft 12       12     144    32.80
Portfolio NAV: 439

The Problem For each portfolio holding, build a string in the following format separated by commas.

[ticker, name, quantity, price, value, %NAV] 
%NAV will always be rounded to 2 decimal places. No percentage signs needed for decimals. You will receive the same input string as the previous question.


Input:
The portfolio and benchmark will be read from Standard Input

You will receive a string in the following format

Portfolio:Benchmark 
where Portfolio is a string in the following format where each holding is separated by the '@' symbol

VOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12
Benchmark is a string in the following format:

ticker, name, quanty, price.
For example:

VOD,Vodafone,10,1.00@GOOG,Google,15,2.00@MSFT,Microsoft,12,3.00

Output:
Print your answer to standard out.

For each portfolio holding, build a string in the following format separated by a comma and a space

[ticker, name, quantity, price, value, %NAV], [ticker, name, quantity, price, value, %NAV] 

Test 1
Test Input Download Test InputRIO,RIO TINTO PLC,746@AAL,ANGLO AMERICAN PLC,271:RIO,RIO TINTO PLC,688,13.9@AAL,ANGLO AMERICAN PLC,293,49.7
Expected Output Download Test Output[AAL, ANGLO AMERICAN PLC, 271, 49.70, 13468.70, 56.50], [RIO, RIO TINTO PLC, 746, 13.90, 10369.40, 43.50]
Test 2
Test Input Download Test InputVOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12:VOD,Vodafone,16,2@GOOG,Google,10,5@MSFT,Microsoft,25,6
Expected Output Download Test Output[GOOG, Google, 15, 5.00, 75.00, 44.91], [MSFT, Microsoft, 12, 6.00, 72.00, 43.11], [VOD, Vodafone, 10, 2.00, 20.00, 11.98]
*/




import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Portfolio{
    
    String ticker;
    String name;
    String quantity;
    String price;
    String value;
    String NAV;
    
    public Portfolio(String ticker, String name, String quantity)
    {
        this.ticker=ticker;
        this.name=name;
        this.quantity=quantity;
        
    }

    
    String get_info()
    {
        return ticker+", "+name+", "+quantity+", "+price+", "+value+", "+NAV;
    }
    
    double get_quantity()
    {
        double quant=Double.parseDouble(quantity);
        return quant;
    }
    
    double get_value()
    {
        double val=Double.parseDouble(value);
        return val;
    }
}

class Benchmark{
    
    String ticker;
    String name;
    String quantity;
    String price;
    
    public Benchmark(String ticker, String name, String quantity, String price)
    {
        this.ticker=ticker;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
        
    }
    
    double get_price()
    {
        double BMprice=Double.parseDouble(price);
        return BMprice;
    }
}


public class Main {
    public static final String SEPARATOR = "@";
    public static final String COLON = ":";
    
    /*
     * Complete the function below.
     *
 	 * Note: The questions in this test build upon each other. We recommend you
	 * copy your solutions to your text editor of choice before proceeding to
	 * the next question as you will not be able to revisit previous questions.
	 */

    static String printHoldingsWithWeight(String inputString) {
        
        String[] div=inputString.split(COLON,2);
        String div1=div[0];
        String div2=div[1];
        
        String[] arr1=div1.split(SEPARATOR);
        String[] arr2=div2.split(SEPARATOR);
        //splitting the string w.r.t @ 
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        //Sorting the array according to teaser
        String result="";
        
        Portfolio[] portfolios=new Portfolio[arr1.length];
        Benchmark[] benchmarks=new Benchmark[arr2.length];
        
        //Assigning values to portfolio and benchmark respectively
        for(int i=0;i<arr1.length;i++)
        {
            String[] temp=arr1[i].split(",");
            int j=0;
            portfolios[i]=new Portfolio(temp[j++],temp[j++],temp[j++]);
        }    
        
        for(int i=0;i<arr2.length;i++)
        {
            String[] temp=arr2[i].split(",");
            int j=0;
            benchmarks[i]=new Benchmark(temp[j++],temp[j++],temp[j++],temp[j++]);
        }
        
        //intialized variable to calculate total portfolio value
        double pfvalue=0;
        
        //calculating the value and NAV for each holding in portfolio
    
        for(int i=0;i<portfolios.length;i++)
        {
            if(portfolios[i].ticker.equals(benchmarks[i].ticker))
            {
                double pfquant=portfolios[i].get_quantity();
                double bmprice=benchmarks[i].get_price();
                
                double hvalue=pfquant*bmprice;
                //calculating the value of each holding;
                pfvalue=pfvalue+hvalue;
                //calculating the total portfolio value
                
                String price=String.format("%.2f",bmprice);
                String value=String.format("%.2f",hvalue);
                portfolios[i].price=price;
                portfolios[i].value=value;
                
            }
        }
        
        for(Portfolio holding:portfolios)
        {
            double hgvalue=holding.get_value();
            
            double hNAV=hgvalue/pfvalue*100;
            String NAV=String.format("%.2f",hNAV);
            
            holding.NAV=NAV;
            
        }
        
        for(Portfolio holding:portfolios)
        {
            result=result+"["+holding.get_info()+"], ";
        }
        
        result=result.substring(0,result.length()-2);
        //dropping the last comma in the string
        
        
        return result;
        
		
    }


    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        String res;
        String _input;
        try {
            _input = in.nextLine();
        } catch (Exception e) {
            _input = null;
        }
        res = printHoldingsWithWeight(_input);
        System.out.println(res);
    }
}
