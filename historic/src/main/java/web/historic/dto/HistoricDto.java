
package web.historic.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class HistoricDto {

    private int id;
    @Positive(message = "Patient id is a positive number")
    private int patient;
    @NotNull(message = "The note is mandatory")
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
