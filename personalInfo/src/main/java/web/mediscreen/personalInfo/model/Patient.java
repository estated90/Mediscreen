package web.mediscreen.personalInfo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Nico
 *
 */
@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patientId")
	private int id;
	@Column(name = "familyName", length = 30, nullable = false)
	@NotBlank(message = "Family name is mandatory")
	private String familyName;
	@Column(name = "givenName", length = 30, nullable = false)
	@NotBlank(message = "Given name is mandatory")
	private String givenName;
	@Column(name = "dateOfBirth")
	@NotBlank(message = "Date of birth cannot be null")
	private LocalDate dateOfBirth;
	@Column(name = "sex", length = 1, nullable = false)
	@NotBlank(message = "You need to fill the sex")
	private String sex;
	@Column(name = "address", length = 150, nullable = false)
	@NotBlank(message = "Adress cannot be blank")
	private String address;
	@Column(name = "phone", length = 12, nullable = false)
	@NotBlank(message = "Phone cannot be blank")
	private String phone;

	/**
	 * 
	 */
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param familyName
	 * @param givenName
	 * @param dateOfBirth
	 * @param sex
	 * @param address
	 * @param phone
	 */
	public Patient(String familyName, String givenName, LocalDate dateOfBirth, String sex, String address,
			String phone) {
		super();
		this.familyName = familyName;
		this.givenName = givenName;
		this.dateOfBirth = dateOfBirth;
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
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	@Override
	public String toString() {
		return "patient [familyName=" + familyName + ", givenName=" + givenName + ", dateOfBirth=" + dateOfBirth
				+ ", sex=" + sex + ", address=" + address + ", phone=" + phone + "]";
	}

}
