package web.historic.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import web.historic.model.Patient;

@FeignClient(value = "microservice-patient", url = "http://localhost:8081")
public interface PatientFeign {
    
    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Patient getPatientById(@PathVariable("id") int id);
}
