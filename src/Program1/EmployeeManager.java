package Program1;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Program #1 that creates new employees, modifies employees, or removes
 * employees from the employees.txt
 * 
 * @author carsnwd/Carson Wood
 *
 */

public class EmployeeManager {
	
	public static void main(String args[]) throws IOException {
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
		mod("521");
	}

	//TO-DO
	private static void mod(String id) throws IOException {
		File employeesFile = new File("resources/employees.txt");
		Scanner fileScanner = new Scanner(employeesFile);
		Scanner userInputScanner = new Scanner(System.in);
		File tempFile = File.createTempFile("appleham", ".txt");
		String fieldToChange = userInputScanner.nextLine();
		fieldToChange = fieldToChange.toLowerCase();
		int changeIdentifyingNum;
		
		//14 Fields for stuff
		//Yeah.....completely gross, I know
		if(fieldToChange == "id"){
			changeIdentifyingNum = 0;
		}else if(fieldToChange == "lastname"){
			changeIdentifyingNum = 1;
		}else if(fieldToChange == "firstname"){
			changeIdentifyingNum = 2;
		}else if(fieldToChange == "department"){
			changeIdentifyingNum = 3;
		}else if(fieldToChange == "title"){
			changeIdentifyingNum = 4;
		}else if(fieldToChange == "salary"){
			changeIdentifyingNum = 5;
		}else if(fieldToChange == "per"){
			changeIdentifyingNum = 6;
		}else if(fieldToChange == "commission"){
			changeIdentifyingNum = 7;
		}else if(fieldToChange == "taxid"){
			changeIdentifyingNum = 8;
		}else if(fieldToChange == "street"){
			changeIdentifyingNum = 9;
		}else if(fieldToChange == "city"){
			changeIdentifyingNum = 10;
		}else if(fieldToChange == "state"){
			changeIdentifyingNum = 11;
		}else if(fieldToChange == "zip"){
			changeIdentifyingNum = 12;
		}else if(fieldToChange == "country"){
			changeIdentifyingNum = 13;
		}else if(fieldToChange == "phone"){
			changeIdentifyingNum = 14;
		}else{
			changeIdentifyingNum = 99;
		}
		
		String newValue = "0";
		PrintWriter p1 = new PrintWriter(tempFile);
	//	tempFile.deleteOnExit();
		p1.print(fileScanner.nextLine() + "\n");
		while(fileScanner.hasNext()){
			String idFromScanner = fileScanner.next();
			if(idFromScanner.equals(id))
			{
				fileScanner.useDelimiter("\t");
				int i = 0;
				while(i<changeIdentifyingNum){
					if((i ==0) && (changeIdentifyingNum != 0)){
						p1.print(idFromScanner + "\t");
					}
					p1.print(fileScanner.next() + "\t");
					i++;
				}
				fileScanner.next();
				p1.print(newValue + "\t");
				fileScanner.reset();
				p1.print(fileScanner.nextLine() + "\n");
				p1.close();
				fileScanner.close();
				userInputScanner.close();
				return;
			}
			else
			{
				p1.print(idFromScanner);
				p1.print(fileScanner.nextLine() + "\n");
			}
		}
		p1.close();
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
		return em;
	}
}