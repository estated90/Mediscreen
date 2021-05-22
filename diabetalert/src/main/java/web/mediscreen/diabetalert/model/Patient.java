package web.mediscreen.diabetalert.model;

import java.time.LocalDate;

/**
 * @author Nico
 *
 */
public class Patient {

	private int id;
	private String family;
	private String given;
	private LocalDate dob;
	private String sex;
	private String address;
	private String phone;

	public Patient(int id, String family, String given, LocalDate dob, String sex, String address, String phone) {
		super();
		this.id = id;
		this.family = family;
		this.given = given;
		this.dob = dob;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @return the given
	 */
	public String getGiven() {
		return given;
	}

	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	
}
