package Program1;

import java.io.*;

/**
 * Program #1 that creates new employees, modifies employees,
 * or removes employees from the employees.txt
 * @author carsnwd/Carson Wood
 *
 */

public class EmployeeManager {
	public static void main(String args[]) {	
		switch(args[0].toLowerCase()){
		case "add":
			add();
			break;
		case "del":
			del();
			break;
		case "mod":
			mod();
			break;
		}
	}
	
	private static void mod() {
		// TODO Auto-generated method stub
	}

	private static void del() {
		// TODO Auto-generated method stub
	}

	public static void add(){
		String employees = "employeesTEMP.txt";
	}
}