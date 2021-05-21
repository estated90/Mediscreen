package web.mediscreen.historic.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import web.mediscreen.historic.model.Patient;

/**
 * @author Nicolas
 * <p>Feign service to call the personnalInfo APIs</p>
 *
 */
@FeignClient(value = "microservice-patient", url = "http://personalinfo:8081")
//@FeignClient(value = "microservice-patient", url = "http://localhost:8081")
public interface PatientFeign {
    
    /**
     * @param id Id of the patient
     * @return The patient from DB
     */
    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Patient getPatientById(@PathVariable("id") int id);
}
