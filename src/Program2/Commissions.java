package Program2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Kalan Kriner, Carson Wood, Abe Loscher
 *
 */
public class Commissions
{
    /**
     * Total sales during the period for use by sales managers and president
     */
    public static double totalSales;
    /**
     * counts how many employees were counted
     */
    public static int count=0;
    /**
     * @param args the dates
     * @throws Exception
     */
    public static void main(String args[]) throws Exception 
    {
        totalSales=0;
        String[] begin=args[0].split("/");
        String[] end= args[1].split("/");
        int startMon=Integer.parseInt(begin[0]);
        int endMon=Integer.parseInt(end[0]);
        int startDay=Integer.parseInt(begin[1]);
        int endDay=Integer.parseInt(end[1]);
        
        
        ArrayList<EmployeeSale> sales=gather(startMon,endMon,startDay,endDay);
        commission(sales);
        
        System.out.println("Read "+count+" employees");

    }

    private static void commission(ArrayList<EmployeeSale> sales) throws FileNotFoundException
    {
        File employeesFile = new File("resources/employees.txt");
        Scanner fileScanner = new Scanner(employeesFile);
        
        //Makes the commission files to write out to
        File com=new File("resources/commissions.txt");
        PrintWriter filePrinter = new PrintWriter(com);
        filePrinter.println("Employee ID"+"\t"+"Total Sales"+"\t"+"Total Commission");
        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        fileScanner.nextLine();
        while(fileScanner.hasNext()){
            String currentLine  = fileScanner.nextLine();
            Scanner currentLineScanner = new Scanner(currentLine);
            String currentID,title,comperc;
            currentLineScanner.useDelimiter("\t");
            currentID = currentLineScanner.next();
            currentLineScanner.next();  currentLineScanner.next();   currentLineScanner.next();
            title=currentLineScanner.next();
            currentLineScanner.next();  currentLineScanner.next();
            comperc = currentLineScanner.next();
            comperc=comperc.split("%")[0];
            double perc=0;
            if(comperc.length()>0)
                perc=Double.parseDouble(comperc);
            
           //Goes through the sale list to match up the employees with their sales to get their commission rates
            double bonus=0,base=0;
            for(int i=0;i<sales.size();i++)
            {
                bonus=0;
                base=0;
                if(title.equals("Sales Manager")||title.equals("President"))
                {
                    base=totalSales;
                    bonus=base*(perc/100);
                    i=sales.size();
                }
                else if(currentID.equals(sales.get(i).getId()) && perc!=0)
                {
                    base=sales.get(i).getBase();
                    bonus=base*(perc/100);
                    i=sales.size();
                }
                    
            }
            
            filePrinter.println(currentID+"\t"+df.format(base)+"\t"+df.format(bonus));
            count++;
            currentLineScanner.close();
        }
        
        filePrinter.close();
        fileScanner.close();
    }
        

    private static ArrayList<EmployeeSale> gather(int startMon, int endMon, int startDay, int endDay) throws FileNotFoundException
    {
        //Creates the Scanner to read from sales
        File salesFile = new File("resources/sales.txt");
        Scanner fileScanner = new Scanner(salesFile);
        boolean start=false,end=false;
        fileScanner.nextLine();
        
        ArrayList<EmployeeSale> sales=new ArrayList<EmployeeSale>();
        while(fileScanner.hasNext()&& !end)
        {
            String currentLine  = fileScanner.nextLine();
            Scanner currentLineScanner = new Scanner(currentLine);
            String id, date, base;
            boolean create=true;
            //fileScanner.nextLine();

            currentLineScanner.useDelimiter("\t");
            id = currentLineScanner.next();
            date = currentLineScanner.next();
            currentLineScanner.next();
            base = currentLineScanner.next();
            double amnt=Double.parseDouble(base);
            
            //Splits the current date to determine if it is part of the current set or not
            String[] strDate=date.split("/");
            int day=Integer.parseInt(strDate[1]);
            int mon=Integer.parseInt(strDate[0]);
            
            //Checks to see if the start date has been reached, once it has, it will just keep this affirmed
            if(start||(mon>=startMon && day>=startDay))
            {
                start=true;
            }
            //If the date is past the end date, it stops looking
            if(mon>endMon||(mon==endMon && day>endDay))
            {
                end=true;
            }
            
            if(start && !end)
            {
                if(sales.size()==0)
                {
                    EmployeeSale es=new EmployeeSale(id,amnt);
                    sales.add(es);
                    continue;
                }

                //Reads through the array list of sales made and adds onto new ones if it exists.
                for(int i=sales.size()-1;i>=0;i--)
                {
                    if(sales.get(i).getId().equals(id))
                    {
                        EmployeeSale temp=sales.remove(i);
                        temp.setBase(amnt+temp.getBase());
                        sales.add(temp);
                        create=false;
                        i=-1;
                    }  
                }
                //If it wasn't found in the list, a new entry is added.
                if(create)
                {               
                    EmployeeSale es=new EmployeeSale(id,amnt);
                    sales.add(es);
                }  
            }
            currentLineScanner.close();
        }
        //totals the sales for the president and sales managers
        for(int j=0;j<sales.size();j++)
        {
            totalSales=totalSales+sales.get(j).getBase();
        }
        
        fileScanner.close();
        return sales;

        
    }

}
