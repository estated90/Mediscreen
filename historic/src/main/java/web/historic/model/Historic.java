package web.historic.model;

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
    private String practitionnerNotesRecommandation;
    
    public Historic() {}
    
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
