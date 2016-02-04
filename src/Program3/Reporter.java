package Program3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import Program1.Employee;
import Program2.Commissions;
import Program2.EmployeeSale;

public class Reporter
{
    static DecimalFormat df = new DecimalFormat("0.00");
    static ArrayList<Employee> acct=new ArrayList<Employee>();
    static ArrayList<Employee> admin=new ArrayList<Employee>();
    static ArrayList<Employee> hr=new ArrayList<Employee>();
    static ArrayList<Employee> ops=new ArrayList<Employee>();
    static ArrayList<Employee> pur=new ArrayList<Employee>();
    static ArrayList<Employee> sales=new ArrayList<Employee>();
    
    public static void main(String args[]) throws Exception 
    {
        df.setRoundingMode(RoundingMode.CEILING);
        double totalSales;
        String[] begin=args[1].split("/");
        String[] end= args[2].split("/");
        int startMon=Integer.parseInt(begin[0]);
        int endMon=Integer.parseInt(end[0]);
        int startDay=Integer.parseInt(begin[1]);
        int endDay=Integer.parseInt(end[1]);
        
        acct.clear();
        admin.clear();
        hr.clear();
        ops.clear();
        pur.clear();
        sales.clear();
        
        
        ArrayList<EmployeeSale> sale=Commissions.gather(startMon,endMon,startDay,endDay);
        totalSales=Commissions.totalSales;
        ArrayList<Employee>emp=readEmps();
        emp=readHours(args[2],emp,sale);
        emp=readAnnum(totalSales,emp);
        setDepartments(emp);
        printReport();
        
        
    }
    
    public static void printReport(){
        Collections.sort(acct,new CustomComparator());
        printDepartment("Accounting",acct);
        Collections.sort(admin,new CustomComparator());
        printDepartment("Administration",admin);
        Collections.sort(hr,new CustomComparator());
        printDepartment("Human Resources",hr);
        Collections.sort(ops,new CustomComparator());
        printDepartment("Operations",ops);
        Collections.sort(pur,new CustomComparator());
        printDepartment("Purchasing",pur);
        Collections.sort(sales,new CustomComparator());
        printDepartment("Sales", sales);
        
    }
    
    public static void printDepartment(String department, ArrayList<Employee> departmentEmployees)
    {
        Employee currentEm;
        double totalTotal,totalPayroll=0; //WEEKTOTAL + COMAMT
        System.out.println("Department: "+department);
        for(int i = 0; i < departmentEmployees.size(); i++){
            currentEm = departmentEmployees.get(i);
            totalTotal = currentEm.getComAmt()+currentEm.getWeekTotal();
        
         System.out.println("\t"+currentEm.getLastName()+","+currentEm.getFirstName()+"\t\t\t\t $\t"+df.format(currentEm.getWeekTotal())+"\t $"
                  +currentEm.getComAmt()+"\t $ \t"+df.format(totalTotal));
          totalPayroll = totalTotal + totalPayroll;
      }
        System.out.println("\t   Total Payroll: "+df.format(totalPayroll));
     }
    
    private static void setDepartments(ArrayList<Employee> emp)
    {
        for(int i=emp.size()-1;i>=0;i--)
        {
            Employee temp=emp.remove(i);

            if(temp.getDepartment().toLowerCase().equals("accounting"))
            {
                acct.add(temp);
            }
            else if(temp.getDepartment().toLowerCase().equals("administration"))
            {
                admin.add(temp);
            }
            else if(temp.getDepartment().toLowerCase().equals("human resources"))
            {
                hr.add(temp);
            }
            else if(temp.getDepartment().toLowerCase().equals("operations"))
            {
                ops.add(temp);
            }
            else if(temp.getDepartment().toLowerCase().equals("purchasing"))
            {
                pur.add(temp);
            }
            else if(temp.getDepartment().toLowerCase().equals("sales"))
            {
                sales.add(temp);
            }
        }
        
    }

    private static ArrayList<Employee> readAnnum( double totalSales, ArrayList<Employee> emp)
    {
        ArrayList<Employee> annum=new ArrayList<Employee>();
        double bonus=0,base=0;
        for(int i=0;i<emp.size();i++)
        {
            bonus=0;
            base=0;
            Employee temp=emp.get(i);
            if(emp.get(i).getPer().toLowerCase().equals("annum"))
            {
                
                double k=Double.parseDouble(df.format(temp.getSalary()/52));
                temp.setWeekTotal(k);
                
                if(temp.getTitle().equals("Sales Manager")||temp.getTitle().equals("President"))
                {
                    temp.setComAmt(Double.parseDouble(df.format(totalSales*(temp.getCommission()/100))));
                }
                
                
            }
            annum.add(temp);
        } 

        return annum;
    }


    private static ArrayList<Employee> readHours(String date, ArrayList<Employee> emp, ArrayList<EmployeeSale> sales) throws FileNotFoundException, ParseException
    {
        boolean set=false;
        File hoursFile = new File("resources/hours.txt");
        Scanner fileScanner = new Scanner(hoursFile);
        fileScanner.nextLine();
        int searchID = 0;
        ArrayList<Employee> hourly=new ArrayList<Employee>();
        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        while(fileScanner.hasNext()){
            String currentLine  = fileScanner.nextLine();

           String[] parsed=currentLine.split("\t");
           double hours = 0;
           
           //Checks to see if it contains the ID
           if(parsed[0].length()>0)
           {
               searchID=Integer.parseInt(parsed[0]);
           }
           DateFormat toFormat = new SimpleDateFormat("M/d");
           DateFormat fromFormat = new SimpleDateFormat("dd-MMM");
           Date fm = fromFormat.parse(parsed[1]);
           String formattedDate = toFormat.format(fm);
           //Sets the hours to what is on the current line
           
           if(date.contains(formattedDate))
           {
               hours=Double.parseDouble(parsed[2]);
               set=true;
           }
           
           //Sets weekly amount
           double base=0;
           for(int i=0;i<emp.size();i++)
           {
               base=0;
               Employee temp=emp.get(i);
               if(searchID==(emp.get(i).getId())&& set)
               {
                   
                  // System.out.println(temp.getSalary());
                   if(hours>40.0)
                   {
                       base=(temp.getSalary()* 40) + (1.5*temp.getSalary())*(hours-40);
                       base= Double.parseDouble(df.format(base));
                   }
                   else
                   {
                       base=temp.getSalary()*hours;
                       base= Double.parseDouble(df.format(base));
                   }
                  // System.out.println(temp.getSalary());
                   temp.setWeekTotal(base);
                   //Sets the commission amount
                   for(int j=0;j<sales.size();j++)
                   {
                       if(sales.get(j).getId().equals(searchID+""))
                       {
                           temp.setComAmt(Double.parseDouble(df.format(sales.get(j).getBase()*(temp.getCommission()/100))));
                       }
                   }
                   set=false;
               } 
               hourly.add(temp);
           }     
       }
        fileScanner.close();       
        return emp;       
    }

    private static ArrayList<Employee> readEmps() throws Exception {
        File employeesFile = new File("resources/employees.txt");
        Scanner fileScanner = new Scanner(employeesFile);
        ArrayList<Employee> emps=new ArrayList<Employee>();
        fileScanner.nextLine();
        
        /**
         * Make sure that we change all types of all params to Employee to Strings instead of different types
         */
        while(fileScanner.hasNext()){
            String currentLine  = fileScanner.nextLine();
            
            Scanner currentLineScanner = new Scanner(currentLine);
            String currentID, taxid;
            String lastName, firstName, department, title, per, street, city, state, country, phone, zip, commission, salary;
            //double salary;
            currentLineScanner.useDelimiter("\t");
            currentID = currentLineScanner.next();
            lastName = currentLineScanner.next();
            firstName = currentLineScanner.next();
            department = currentLineScanner.next();
            title = currentLineScanner.next();
            salary = currentLineScanner.next();
            per = currentLineScanner.next();
            commission = currentLineScanner.next();
            taxid = currentLineScanner.next();
            street = currentLineScanner.next();
            city = currentLineScanner.next();
            state = currentLineScanner.next();
            zip = currentLineScanner.next();
            country = currentLineScanner.next();
            phone = currentLineScanner.next();
            commission=commission.split("%")[0];
            double perc=0;
            if(commission.length()>0)
                perc=Double.parseDouble(commission);
            else
                perc=0;
        
            Employee em = new Employee(Integer.parseInt(currentID), firstName, lastName, department, title,
                    Double.parseDouble(salary), per, perc, Integer.parseInt(taxid), street, city, state, zip,
                    country, phone);
            
            emps.add(em);
            currentLineScanner.close();
        
        }
        
        fileScanner.close();
        return emps;
    }

}

class CustomComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) 
    {
        return e1.getLastName().compareTo(e2.getLastName());
    }
}
