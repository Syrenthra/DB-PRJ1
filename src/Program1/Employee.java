package Program1;

/**
 * Employee object to store new employee info that needs to be written to
 * records.
 * 
 * @author carsnwd/Carson
 *
 */
public class Employee {

	private int id, /*zip,*/ taxID;
	double salary, commission, weekTotal, comAmt;
	String firstName, lastName, department, title, per, street, city, state,
			country, phone, zip;

	public Employee(int id, String firstName, String lastName,
			String department, String title, double salary, String per,
			double commission, int taxID, String street, String city,
			String state, /*int zip*/ String zip, String country, String phone) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.city = city;
		this.commission = commission;
		this.country = country;
		this.per = per;
		this.phone = phone;
		this.salary = salary;
		this.state = state;
		this.street = street;
		this.taxID = taxID;
		this.title = title;
		this.zip = zip;
		this.weekTotal = 0;
		this.comAmt = 0;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

//	/**
//	 * @return the zip
//	 */
//	public int getZip() {
//		return zip;
//	}
//
//	/**
//	 * @param zip the zip to set
//	 */
//	public void setZip(int zip) {
//		this.zip = zip;
//	}
	
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}


	/**
	 * @return the taxID
	 */
	public int getTaxID() {
		return taxID;
	}

	/**
	 * @param taxID the taxID to set
	 */
	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @return the commission
	 */
	public double getCommission() {
		return commission;
	}

	/**
	 * @param commission the commission to set
	 */
	public void setCommission(double commission) {
		this.commission = commission;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the per
	 */
	public String getPer() {
		return per;
	}

	/**
	 * @param per the per to set
	 */
	public void setPer(String per) {
		this.per = per;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the weekTotal
	 */
	public double getWeekTotal() {
		return weekTotal;
	}

	/**
	 * @param weekTotal the weekTotal to set
	 */
	public void setWeekTotal(double weekTotal) {
		this.weekTotal = weekTotal;
	}

	/**
	 * @return the comAmt
	 */
	public double getComAmt() {
		return comAmt;
	}

	/**
	 * @param comAmt the comAmt to set
	 */
	public void setComAmt(double comAmt) {
		this.comAmt = comAmt;
	}

}
