package web.historic.model;

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

    public Patient() {
	super();
    }

    

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
     * @param id the id to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * @return the family
     */
    public String getFamily() {
	return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
	this.family = family;
    }

    /**
     * @return the given
     */
    public String getGiven() {
	return given;
    }

    /**
     * @param given the given to set
     */
    public void setGiven(String given) {
	this.given = given;
    }

    /**
     * @return the dob
     */
    public LocalDate getDob() {
	return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(LocalDate dob) {
	this.dob = dob;
    }

    /**
     * @return the sex
     */
    public String getSex() {
	return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
	this.sex = sex;
    }

    /**
     * @return the address
     */
    public String getAddress() {
	return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
	this.address = address;
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

}
