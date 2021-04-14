package web.mediscreen.personalinfo.dto;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import web.mediscreen.personalinfo.validators.FieldMatch;

@FieldMatch
public class PatientDto {
    @Id
    private int id;
    @NotBlank(message = "Family name is mandatory")
    private String family;
    @NotBlank(message = "Given name is mandatory")
    private String given;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @NotBlank(message = "You need to fill the sex")
    private String sex;
    @NotBlank(message = "Adress cannot be blank")
    private String address;
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
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
