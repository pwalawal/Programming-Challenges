/*
Assuming the Portfolio NAV (total value of holdings) has to stay constant, calculate the transactions you need to make so that the %NAV values of your portfolio holdings match the %NAV values of the benchmark holdings.

Hint: Make sure the relative weightings of each holding are equal between your portfolio and the benchmark.


Input:
Read the portfolio and benchmark from standard input.

You will receive a String in the following format

Portfolio:Benchmark 
where Portfolio is a String in the following format where each holding is separated by the '@' symbol

VOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12
Benchmark is a String in the following format:

ticker, name, quanty, price
For example:

VOD,Vodafone,10,1.00@GOOG,Google,15,2.00@MSFT,Microsoft,12,3.00
where each holding is separated by the '@' symbol


Output:
Print your answer to standard out.

Build a string in the following format for each transaction

[transaction type, ticker, quantity], [transaction type, ticker, quantity]
Order each transaction alphabetically by ticker.

Reminder - all decimals should be rounded to 2 decimal places.


Test 1
Test Input Download Test InputRIO,RIO TINTO PLC,746@AAL,ANGLO AMERICAN PLC,271:RIO,RIO TINTO PLC,688,13.9@AAL,ANGLO AMERICAN PLC,293,49.7
Expected Output Download Test Output[BUY, AAL, 18.51], [SELL, RIO, -66.19]
Test 2
Test Input Download Test InputVOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12:VOD,Vodafone,16,2@GOOG,Google,10,5@MSFT,Microsoft,25,6
Expected Output Download Test Output[SELL, GOOG, -7.80], [BUY, MSFT, 6.00], [BUY, VOD, 1.52]
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
    
    public Portfolio(String ticker, String name, String quantity)
    {
        this.ticker=ticker;
        this.name=name;
        this.quantity=quantity;
        
    }
    
    String get_info()
    {
        return ticker+", "+name+", "+quantity;
    }
    
    double get_quantity()
    {
        double quant=Double.parseDouble(quantity);
        return quant;
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
    
    String get_info()
    {
        return ticker+", "+name+", "+quantity;
    }
    
    double get_quantity()
    {
        double quant=Double.parseDouble(quantity);
        return quant;
    }
    
    double get_price()
    {
        double BMprice=Double.parseDouble(price);
        return BMprice;
    }
}

class Transaction{
    
    String type;
    String ticker;
    String quantity;
    
    public Transaction(String type, String ticker, String quantity)
    {
        this.type=type;
        this.ticker=ticker;
        this.quantity=quantity;
        
    }
    
    String get_info()
    {
        return type+", "+ticker+", "+quantity;
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


    static String generateTransactions(String inputString) {
        
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
        Transaction[] transactions=new Transaction[portfolios.length];
        
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
        
        double bmvalue=0,pfvalue=0;
        HashMap<String,Double> hashmapBM=new HashMap<String,Double>();
        //hashmap for storing values of holdings of benchmarks
        HashMap<String,Double> hashmapPF=new HashMap<String,Double>();
        //hashmap for storing values of holdings of portfolio
        
        //Calculating NAV for benchmarks and portfolio
        for(int i=0;i<benchmarks.length;i++)
        {
            
                double bmquant=benchmarks[i].get_quantity();
                double bmprice=benchmarks[i].get_price();
                double pfquant=portfolios[i].get_quantity();
                
                
                double hvalue=bmquant*bmprice;
                //calculating the value of each holding for benchmarks;
                bmvalue=bmvalue+hvalue;
                //calculating the total NAV value of benchmark
                
                
                double pvalue=pfquant*bmprice;
                //calculating the value of each holding for portfolios;
                pfvalue=pfvalue+pvalue;
                //calculating the total NAV value of portfolio
                
                hashmapBM.put(benchmarks[i].ticker,hvalue);
                hashmapPF.put(benchmarks[i].ticker,pvalue);
                //Storing the values of holdings for portfolio and benchmarks in their respective hashmap
                

        }
        

        int count=0;
        for(int i=0;i<benchmarks.length;i++)
        {
            double hgvalue=hashmapBM.get(benchmarks[i].ticker);
            
            double hNAV=hgvalue/bmvalue;
            //Calculating the individual %NAV of benchmark holding
            
            double reqQuant=hNAV*pfvalue/benchmarks[i].get_price();
            //Calculating to find desired quantity of holding in portfolio
            double pfquant=portfolios[i].get_quantity();
            //Actual quantity of holding in a portfolio

            double transQuant=reqQuant-pfquant;
            //calculting the difference
           
            String transQuantity=String.format("%.2f",transQuant);
            
            if(transQuant<0)
            {
                transactions[count++]=new Transaction("SELL",portfolios[i].ticker,transQuantity);
                    
            }
            else
            {
                transactions[count++]=new Transaction("BUY",portfolios[i].ticker,transQuantity);
            }
                
        }    
        for(Transaction tn : transactions)
        {
            result=result+"["+tn.get_info()+"], ";
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
        res = generateTransactions(_input);
        System.out.println(res);
    }
}
