package web.mediscreen.historic.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="historic")
public class Historic {

    @Transient
    public static final String SEQUENCE_NAME = "historic_sequence";
    @Id 
    private int id;
    private String patient;
    private int patId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String practitionnerNotesRecommandation;
    
    public Historic() {
    	//Default constructor
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
    
    
}
