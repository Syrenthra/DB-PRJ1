package Program3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Program1.Employee;

/**
 * 
 * 
 * @author Carson Wood
 *
 */
public class hours {
	public static void main(String args[]) throws ParseException,
			FileNotFoundException {
		// String startDate = formatDate(args[0]);
		// String endDate = formatDate(args[1]);
		//
		// System.out.println(startDate + " to " + endDate);
		//findHourlyEmployees();

		/**
		 * These methods may not be used in the final version, but I am just
		 * trying to break up the problem one by one and solve each part.
		 */
	}

	/**
	 * Converts a mm/dd/yyy date format to a mmm-dd format. It converts the date
	 * from the input arguments format to the format in the hours.txt file.
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws ParseException
	 * @author Carson Wood
	 */
	private static String formatDate(String dateToFormat) throws ParseException {
		DateFormat fromFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat toFormat = new SimpleDateFormat("MMM-dd");

		Date date = fromFormat.parse(dateToFormat);
		String formattedDate = toFormat.format(date);
		// System.out.println(formattedDate);
		return formattedDate;
	}

	/**
	 * Finds all the hourly employees in employees.txt and 
	 * makes employee objects for them.
	 *
	 *NOTES**
	 *Use employee object approach or go a different way???
	 *
	 * 
	 * @author Carson Wood
	 * @throws FileNotFoundException
	 */
	private static void findHourlyEmployees() throws FileNotFoundException {

		File employeesFile = new File("resources/employees.txt");
		Scanner fileScanner = new Scanner(employeesFile);
		int i = 0;
		ArrayList<Employee> hourlyEmployees = new ArrayList<Employee>(); //Stores a list of all hourly employees
		Employee hourlyEmployee; //Temp employee object
		String per; //Hourly, annually, etc
		String[] delims; 
		String lineFromFile;
		
		while (fileScanner.hasNextLine()) {
			lineFromFile = fileScanner.nextLine();
			delims = lineFromFile.split("	"); //Splits line up and stores each part in an array
			per = delims[6]; //Gets the per pay (hourly, annually, etc)
			
			if (per.equals("Hour")) { //If an hourly employee, make an employee object for said employee and store it in an arraylist
				hourlyEmployee = new Employee(Integer.parseInt(delims[0]),
						delims[1], delims[2], delims[3], delims[4], Double.parseDouble(delims[5]),
						delims[6], delims[7], Integer.parseInt(delims[8]), delims[9], delims[10],
						delims[11], delims[12], delims[13], delims[14]); 
				hourlyEmployees.add(hourlyEmployee);
				i++;
			}
		}
		
		//Prints out all hourly employee ids as a test.
//		for(i = 0; i < hourlyEmployees.size(); i++){
//			System.out.println(hourlyEmployees.get(i).getId());
//		}
		fileScanner.close();

	}
}
