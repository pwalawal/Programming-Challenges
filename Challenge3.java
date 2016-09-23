/*

Print out the Benchmark using the standard format.

Remember to use objects to model the domain and copy your answer for the next question.
Input:
Read the portfolio and benchmark from standard input.

You will receive a string in the following format

Portfolio:Benchmark
The Portfolio and Benchmark will be represented in the following format ticker, name, quantity and each holding is separated by the '@' symbol

VOD,Vodafone,10@GOOG,Google,15@MSFT,Microsoft,12

Output:
Print the benchmark to standard output

It should be a string in the following format for each holding with each benchmark holding separated by a comma and a space.

[ticker, name, quantity], [ticker, name, quantity]
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

    static String printHoldings(String portfolioString) {
        
        String[] div=portfolioString.split(COLON,2);
        String div1=div[0];
        String div2=div[1];
        
        String[] arr=div2.split(SEPARATOR);
        //splitting the string w.r.t @ 
        Arrays.sort(arr);
        //Sorting the array according to teaser
        String result="";
        Benchmark[] holding=new Benchmark[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            String[] temp=arr[i].split(",");
            int j=0;
            holding[i]=new Benchmark(temp[j++],temp[j++],temp[j++]);
            
        }
        
        for(Benchmark bm : holding)
        {
            result=result+"["+bm.get_info()+"], ";
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
