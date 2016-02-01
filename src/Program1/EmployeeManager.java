package Program1;

import java.io.*;
import java.util.Scanner;

/**
 * Program #1 that creates new employees, modifies employees, or removes
 * employees from the employees.txt
 * 
 * @author carsnwd/Carson Wood
 *
 */

public class EmployeeManager {
	
	public static void main(String args[]) throws Exception {
		/*switch (args[0].toLowerCase()) {
		case "add":
			add();
			break;
		case "del":
			del(args[1]);
			break;
		case "mod":
			mod(args[1]);
			break;
		}*/
		mod("1233");
	}

	//TO-DO
	private static void mod(String id) throws Exception {
		File employeesFile = new File("resources/employees.txt");
		Scanner fileScanner = new Scanner(employeesFile);
		Scanner userInputScanner = new Scanner(System.in);
		
		//Makes a temp file to save everything to, also asks for field to be changed
		File tempFile = File.createTempFile("appleham", ".txt");
		System.out.print("Enter field to change:  ");
		String fieldToChange = userInputScanner.nextLine();
		System.out.println("");
		fieldToChange = fieldToChange.toLowerCase();
		int changeIdentifyingNum;
		
		//14 Fields for stuff
		//Yeah.....completely gross, I know
		if(fieldToChange.equals("id")){
			changeIdentifyingNum = 0;
		}else if(fieldToChange.equals("lastname")){
			changeIdentifyingNum = 1;
		}else if(fieldToChange.equals("firstname")){
			changeIdentifyingNum = 2;
		}else if(fieldToChange.equals("department")){
			changeIdentifyingNum = 3;
		}else if(fieldToChange.equals("title")){
			changeIdentifyingNum = 4;
		}else if(fieldToChange.equals("salary")){
			changeIdentifyingNum = 5;
		}else if(fieldToChange.equals("per")){
			changeIdentifyingNum = 6;
		}else if(fieldToChange.equals("commission")){
			changeIdentifyingNum = 7;
		}else if(fieldToChange.equals("taxid")){
			changeIdentifyingNum = 8;
		}else if(fieldToChange.equals("street")){
			changeIdentifyingNum = 9;
		}else if(fieldToChange.equals("city")){
			changeIdentifyingNum = 10;
		}else if(fieldToChange.equals("state")){
			changeIdentifyingNum = 11;
		}else if(fieldToChange.equals("zip")){
			changeIdentifyingNum = 12;
		}else if(fieldToChange.equals("country")){
			changeIdentifyingNum = 13;
		}else if(fieldToChange.equals("phone")){
			changeIdentifyingNum = 14;
		}else{
			fileScanner.close();
			userInputScanner.close();
			throw new Exception(fieldToChange +  " is not a valid edit type!");
		}
		
		String newValue = null;
		System.out.print("Enter new value:  ");
		newValue = userInputScanner.nextLine();
		System.out.println("");
		PrintWriter filePrinter = new PrintWriter(tempFile);
		tempFile.deleteOnExit();
		filePrinter.print(fileScanner.nextLine() + "\n");
		while(fileScanner.hasNext()){
			String idFromScanner = fileScanner.next();
			if(idFromScanner.equals(id))
			{
				fileScanner.useDelimiter("\t");
				int i = 0;
				while(i<changeIdentifyingNum-1){
					if((i ==0) && (changeIdentifyingNum != 0)){
						filePrinter.print(idFromScanner + "\t");
					}
					fileScanner.next();
					filePrinter.print(fileScanner.next() + "\t");
					i++;
				}
				filePrinter.print(newValue + "\t");
				fileScanner.reset();
				filePrinter.print(fileScanner.nextLine() + "\n");
			}
			else
			{
				filePrinter.print(idFromScanner);
				filePrinter.print(fileScanner.nextLine() + "\n");
			}
		}
		//Rename to employees.txt and then delete the old file
		tempFile.renameTo(employeesFile);
		
		filePrinter.close();
		fileScanner.close();
		userInputScanner.close();
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

		// FILE WRITER
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
			out.print(newEmployee.getPhone());
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
		System.out.println("Enter employee ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter last name: ");
		String lastName = scanner.nextLine();
		System.out.println("Enter first name: ");
		String firstName = scanner.nextLine();
		System.out.println("Enter department: ");
		String department = scanner.nextLine();
		System.out.println("Enter title: ");
		String title = scanner.nextLine();
		System.out.println("Enter salary: ");
		double salary = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter per: ");
		String per = scanner.nextLine();
		System.out.println("Enter commission: ");
		double commission = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter taxID: ");
		int taxID = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter street: ");
		String street = scanner.nextLine();
		System.out.println("Enter city: ");
		String city = scanner.nextLine();
		System.out.println("Enter state");
		String state = scanner.nextLine();
		System.out.println("Enter zip: ");
		int zip = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter country: ");
		String country = scanner.nextLine();
		System.out.println("Enter phone: ");
		String phone = scanner.nextLine();

		Employee em = new Employee(id, firstName, lastName, department, title,
				salary, per, commission, taxID, street, city, state, zip,
				country, phone);
		scanner.close();
		return em;
	}
}