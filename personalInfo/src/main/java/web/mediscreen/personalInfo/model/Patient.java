package web.mediscreen.personalInfo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import web.mediscreen.personalInfo.validators.FieldMatch;

/**
 * @author Nico
 *
 */
@Entity
@FieldMatch
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private int id;
    @Column(name = "family", nullable = false)
    @NotBlank(message = "Family name is mandatory")
    private String family;
    @Column(name = "given", nullable = false)
    @NotBlank(message = "Given name is mandatory")
    private String given;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "sex", length = 1, nullable = false)
    @NotBlank(message = "You need to fill the sex")
    private String sex;
    @Column(name = "address", length = 150, nullable = false)
    @NotBlank(message = "Adress cannot be blank")
    private String address;
    @Column(name = "phone", length = 12, nullable = false)
    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    public Patient() {
	super();
    }

    public Patient(@NotBlank(message = "Family name is mandatory") String family,
	    @NotBlank(message = "Given name is mandatory") String given,
	    @PastOrPresent(message = "Date of birth cannot be null") LocalDate dateOfBirth,
	    @NotBlank(message = "You need to fill the sex") String sex,
	    @NotBlank(message = "Adress cannot be blank") String address,
	    @NotBlank(message = "Phone cannot be blank") String phone) {
	super();
	this.family = family;
	this.given = given;
	this.dob = dateOfBirth;
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
