package Program1;

import java.io.*;
import java.util.Scanner;

/**
 * Program #1 that creates new employees, modifies employees, or removes
 * employees from the employees.txt
 * 
 * @author Carson Wood, Kalan Kriner, Abraham Loscher
 *
 */

public class EmployeeManager {
	
	public static void main(String args[]) throws Exception {
		//Invalid input handling
		String employeeID;
		if(args[0] == null){ //If the user entered no arguements, ask them to.
			Scanner userInputScanner = new Scanner(System.in);
			System.out.print("Please enter an employee ID to perform a " + args[0] + " command: ");
			employeeID = userInputScanner.nextLine();
		}else{ //Otherwise if they did, throw the argument into an employeeID string.
			employeeID = args[1]; 
		}
	
		switch (args[0].toLowerCase()) {
		case "add":
			add();
			break;
		case "del":
			del(employeeID);
			break;
		case "mod":
			mod(Integer.parseInt(employeeID));
			break;
		default:
			System.out.print("The command you entered does not exist.");
			break;
		}
		//mod(1233);
	}

	/**
	 * Modifies the given employee based on their ID
	 */
	@SuppressWarnings("resource")
    private static void mod(int id) throws Exception {
		File employeesFile = new File("resources/employees.txt");
		Scanner fileScanner = new Scanner(employeesFile);
		Scanner userInputScanner = new Scanner(System.in);
		
		//Makes a temp file to save everything to, also asks for field to be changed
		File tempFile = File.createTempFile("emptemp",".txt",new File("resources/"));
		PrintWriter filePrinter = new PrintWriter(tempFile);
		System.out.print("Enter field to change:  ");
		String fieldToChange = userInputScanner.nextLine();
		System.out.println("");
		fieldToChange = fieldToChange.toLowerCase();

		
		//What the new value will be
		String newValue = null;
        System.out.print("Enter new value:  ");
        newValue = userInputScanner.nextLine();
        System.out.println("");
        //Prints header
        filePrinter.print(fileScanner.nextLine() + "\n");
        
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
		
		//Finds the right value to change and changes it when it is the right ID
            
            if(Integer.parseInt(currentID)==id)
            {
                if(fieldToChange.equals("id")){
                    currentID=newValue;
                }else if(fieldToChange.equals("lastname")){
                    lastName=newValue;
                }else if(fieldToChange.equals("firstname")){
                    firstName=newValue;
                }else if(fieldToChange.equals("department")){
                    department=newValue;
                }else if(fieldToChange.equals("title")){
                    title=newValue;
                }else if(fieldToChange.equals("salary")){
                    salary=newValue;
                }else if(fieldToChange.equals("per")){
                    per=newValue;
                }else if(fieldToChange.equals("commission")){
                    commission=newValue;
                }else if(fieldToChange.equals("taxid")){
                    taxid=newValue;
                }else if(fieldToChange.equals("street")){
                    street=newValue;
                }else if(fieldToChange.equals("city")){
                    city=newValue;
                }else if(fieldToChange.equals("state")){
                    state=newValue;
                }else if(fieldToChange.equals("zip")){
                    zip=newValue;
                }else if(fieldToChange.equals("country")){
                    country=newValue;
                }else if(fieldToChange.equals("phone")){
                    phone=newValue;
                }else{
                    fileScanner.close();
                    userInputScanner.close();
                    throw new Exception(fieldToChange +  " is not a valid edit type!");
                }
            }
		    filePrinter.println(currentID + "\t" + lastName + "\t" + firstName + "\t" + department + "\t" + title
		            + "\t" + salary + "\t" + per + "\t" + commission + "\t" + taxid + "\t" + street + "\t" + city
		            + "\t" + state + "\t" + zip + "\t" + country + "\t" + phone);

			
		}
		//Delete the old file then rename to employees.txt
		
		filePrinter.close();
		fileScanner.close();
		userInputScanner.close();
		employeesFile.delete();
		tempFile.renameTo(new File("resources/employees.txt"));
	}

	/**
	 * Deletes an employee from the file given an id.
	 * @param id of employee to delete
	 * @throws IOException
	 */
	private static void del(String id) throws IOException {
		File employeesFile = new File("employees.txt");
		File tempEmployeesFile = new File("temp.txt");
		Scanner fileScanner = new Scanner(employeesFile);
		while (fileScanner.hasNextLine()) {
		   String lineFromFile = fileScanner.nextLine();
		   String[] delims = lineFromFile.split("	");
		   String idInFile = delims[0];
		   if(idInFile.contains(id)) {
		       System.out.println("Removing" +id+delims[1]+delims[2]);
			   continue;
		   }
		   try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(tempEmployeesFile, true)))) {
			   out.print(lineFromFile);
			   out.print("\n");
		   }
		}
		fileScanner.close();
		tempEmployeesFile.renameTo(employeesFile);
	}

	/**
	 * Create and adds new employee to system.
	 * @throws IOException 
	 */
	public static void add() throws IOException {
		// INPUT EMPLOYEE INFO
		Employee newEmployee = createNewEmployee();

		// WRITE IT BACK TO THE .TXT FILE
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter("employees.txt", true)))) {
			out.print("\n");
			out.print(newEmployee.getId());
			out.print("\t");
			out.print(newEmployee.getLastName());
			out.print("\t");
			out.print(newEmployee.getFirstName());
			out.print("\t");
			out.print(newEmployee.getDepartment());
			out.print("\t");
			out.print(newEmployee.getTitle());
			out.print("\t");
			out.print(newEmployee.getSalary());
			out.print("\t");
			out.print(newEmployee.getPer());
			out.print("\t");
			out.print(newEmployee.getCommission());
			out.print("\t");
			out.print(newEmployee.getTaxID());
			out.print("\t");
			out.print(newEmployee.getStreet());
			out.print("\t");
			out.print(newEmployee.getCity());
			out.print("\t");
			out.print(newEmployee.getState());
			out.print("\t");
			out.print(newEmployee.getZip());
			out.print("\t");
			out.print(newEmployee.getCountry());
			out.print("\t");
			out.println(newEmployee.getPhone());
			out.close();
		}
	}

	/**
	 * Input employee info to command line then create a new employee object
	 * that can be read into the employees.txt file
	 * 
	 * @return Employee em
	 */
	private static Employee createNewEmployee() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter new employee ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter new last name: ");
		String lastName = scanner.nextLine();
		System.out.println("Enter new first name: ");
		String firstName = scanner.nextLine();
		System.out.println("Enter new department: ");
		String department = scanner.nextLine();
		System.out.println("Enter new title: ");
		String title = scanner.nextLine();
		System.out.println("Enter new salary: ");
		double salary = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter new per: ");
		String per = scanner.nextLine();
		System.out.println("Enter new commission: ");
		double commission = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter new taxID: ");
		int taxID = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter new street: ");
		String street = scanner.nextLine();
		System.out.println("Enter new city: ");
		String city = scanner.nextLine();
		System.out.println("Enter new state");
		String state = scanner.nextLine();
		System.out.println("Enter new zip: ");
		//int zip = scanner.nextInt();
		//scanner.nextLine();
		String zip = scanner.nextLine();
		System.out.println("Enter new country: ");
		String country = scanner.nextLine();
		System.out.println("Enter new phone: ");
		String phone = scanner.nextLine();
		
		System.out.println("Writing employee");

		Employee em = new Employee(id, firstName, lastName, department, title,
				salary, per, commission, taxID, street, city, state, zip,
				country, phone);
		scanner.close();
		return em;
	}
}