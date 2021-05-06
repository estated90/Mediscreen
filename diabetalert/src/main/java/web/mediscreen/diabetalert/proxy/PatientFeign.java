package web.mediscreen.diabetalert.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import web.mediscreen.diabetalert.model.Patient;

//For Docker use :
@FeignClient(value = "microservice-patient", url = "http://personalinfo:8081")
//For local development
//@FeignClient(value = "microservice-patient", url = "http://localhost:8081")
public interface PatientFeign {
    
    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Patient getPatientById(@PathVariable("id") int id);
}
