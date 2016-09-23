/*
Input:
Read your portfolio from standard input.

The Portfolio will be represented in the following format: ticker, name, quantity and each holding is separated by the '@' symbol.

For example:

VOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12

Output:
Print your result to standard output

It should be a string in the following format for each holding with each holding separated by a comma and a space.

 [ticker, name, quantity], [ticker, name, quantity]
Order the holdings alphabetically ascending by ticker



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
}

public class Main {
    


    
    public static final String SEPARATOR = "@";
    /*
     * Complete the function below.
     *
 	 * Note: The questions in this test build upon each other. We recommend you
	 * copy your solutions to your text editor of choice before proceeding to
	 * the next question as you will not be able to revisit previous questions.
	 */

    static String printHoldings(String portfolioString) {

        String[] arr=portfolioString.split("@");
        //splitting the string w.r.t @ 
        Arrays.sort(arr);
        //Sorting the array according to teaser
        String result="";
        Portfolio[] holding=new Portfolio[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            String[] temp=arr[i].split(",");
            int j=0;
            holding[i]=new Portfolio(temp[j++],temp[j++],temp[j++]);
            
        }
        
        for(Portfolio pf:holding)
        {
            result=result+"["+pf.get_info()+"], ";
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
        res = printHoldings(_input);
        System.out.println(res);
    }
}
