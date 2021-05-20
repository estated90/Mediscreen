
package web.historic.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class HistoricDto {

    private int id;
    @NotBlank(message="Patient name is mandatory")
    @NotNull(message="Patient name cannot be null")
    private String patient;
    @Positive(message = "Patient id is a positive number")
    @NotNull(message = "Patient id cannot be null")
    private int patId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @NotBlank(message = "The note is mandatory")
    @NotNull(message = "The note cannot be null")
    private String practitionnerNotesRecommandation;
    
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
     * @return the patient
     */
    public String getPatient() {
        return patient;
    }
    /**
     * @param patient the patient to set
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }
    /**
     * @return the patId
     */
    public int getPatId() {
        return patId;
    }
    /**
     * @param patId the patId to set
     */
    public void setPatId(int patId) {
        this.patId = patId;
    }
    /**
     * @return the practitionnerNotesRecommandation
     */
    public String getPractitionnerNotesRecommandation() {
        return practitionnerNotesRecommandation;
    }
    /**
     * @param practitionnerNotesRecommandation the practitionnerNotesRecommandation to set
     */
    public void setPractitionnerNotesRecommandation(String practitionnerNotesRecommandation) {
        this.practitionnerNotesRecommandation = practitionnerNotesRecommandation;
    }
	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the modifiedAt
	 */
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}  
    
}
