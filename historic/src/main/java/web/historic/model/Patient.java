package web.historic.model;

import java.time.LocalDate;

/**
 * @author Nico
 *
 */
@SuppressWarnings("unused")
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

}
