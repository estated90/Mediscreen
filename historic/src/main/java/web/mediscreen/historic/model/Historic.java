package web.mediscreen.historic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Historic {

    @Id
    private int id;
    private String Patient;
    private String practitionnerNotesRecommandation;
    /**
     * @param id
     * @param patient
     * @param practitionnerNotesRecommandation
     */
    public Historic(int id, String patient, String practitionnerNotesRecommandation) {
	super();
	this.id = id;
	Patient = patient;
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
    public String getPatient() {
        return Patient;
    }
    /**
     * @param patient the patient to set
     */
    public void setPatient(String patient) {
        Patient = patient;
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
