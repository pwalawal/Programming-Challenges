/*
Programming Challenge Description:
Take the following example:

Portfolio

Ticker      Name          Quantity
VOD         Vodafone      10
GOOG        Google        15
MSFT        Microsoft     12    
Benchmark

Ticker     Name       Quantity
VOD        Vodafone   6
GOOG       Google     10
MSFT       Microsoft  25
Passive portfolio management is act of trying to make our portfolio "look like" the benchmark.

In the example above, we currently hold 10 shares of Vodafone but there are 16 shares in the benchmark, so we need to buy 6 shares of Vodafone in order to match it.

The Problem: You will receive a string in the following format Portfolio:Benchmark where Portfolio is a string in the format of question 1 and 2 and Benchmark is also a string in the same format.

Calculate the transactions you need to make for your portfolio from question 2 to match the benchmark.

Build a string in the following format for each transaction

[transaction type, ticker, quantity]
In the example above, my string would say

[SELL, GOOG, 5.00], [BUY, MSFT, 13.00], [BUY, VOD, 6.00]
Order alphabetically by ticker.

Recommendation

Create an object to hold the transactions as it will be used in further problems
Quantities must be formatted to 2 decimal places
All numbers are positive
Remember to copy your code for the following question.


Input:
The portfolio and benchmark is read from standard input. You will receive a string in the following format

Portfolio:Benchmark.
The Portfolio will be represented in the following format: ticker, name, quantity and each holding is separated by the '@' symbol:

VOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12
Benchmark is also a string in the same format.


Output:
Print the transaction list to standard out.

Build a string in the following format for each transaction and order each transaction alphabetically by ticker.

[transaction type, ticker, quantity],  [transaction type, ticker, quantity]

Test 1
Test Input Download Test InputBLK,BlackRock,65@JPM,JPMorgan,78@BK,Bank of New York Mellon,13@WFC,Wells Fargo & Co,25:BLK,BlackRock,52@JPM,JPMorgan,19@BK,Bank of New York Mellon,64@WFC,Wells Fargo & Co,125
Expected Output Download Test Output[BUY, BK, 51.00], [SELL, BLK, 13.00], [SELL, JPM, 59.00], [BUY, WFC, 100.00]
Test 2
Test Input Download Test InputVOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12:VOD,Vodafone,16@GOOG,Google,10@MSFT,Microsoft,25
Expected Output Download Test Output[SELL, GOOG, 5.00], [BUY, MSFT, 13.00], [BUY, VOD, 6.00]

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
    
    public Benchmark(String ticker, String name, String quantity)
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
    public static String SEPARATOR = "@";
    public static final String COLON = ":";
    
    /*
     * Complete the function below.
     *
 	 * Note: The questions in this test build upon each other. We recommend you
	 * copy your solutions to your text editor of choice before proceeding to
	 * the next question as you will not be able to revisit previous questions.
	 */

    static String generateTransactions(String input) {
        
        String[] div=input.split(COLON,2);
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
            benchmarks[i]=new Benchmark(temp[j++],temp[j++],temp[j++]);
        }   
        int count=0;
        //Assumption is that there is no exclusive ticker in portfolio or benchmark
        for(int i=0;i<portfolios.length;i++)
        {
            if(portfolios[i].ticker.equals(benchmarks[i].ticker))
            {
                double pfquant=portfolios[i].get_quantity();
                double bmquant=benchmarks[i].get_quantity();
                
                double quant=Math.abs(pfquant-bmquant);
                
                String quantity=String.format("%.2f",quant);
                
                if(pfquant>bmquant)
                {
                    transactions[count++]=new Transaction("SELL",portfolios[i].ticker,quantity);
                    
                }
                else
                {
                    transactions[count++]=new Transaction("BUY",portfolios[i].ticker,quantity);
                }
                
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
