package web.historic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Historic {

    @Id
    private int id;
    private int patient;
    private String practitionnerNotesRecommandation;
    
    /**
     * 
     */
    public Historic() {
	super();
    }
    /**
     * @param id
     * @param patient
     * @param practitionnerNotesRecommandation
     */
    public Historic(int id, int patient, String practitionnerNotesRecommandation) {
	super();
	this.id = id;
	this.patient = patient;
	this.practitionnerNotesRecommandation = practitionnerNotesRecommandation;
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
    public int getPatient() {
        return patient;
    }
    /**
     * @param patient the patient to set
     */
    public void setPatient(int patient) {
        this.patient = patient;
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
