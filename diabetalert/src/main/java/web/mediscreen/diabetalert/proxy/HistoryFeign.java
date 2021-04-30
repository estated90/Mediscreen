package web.mediscreen.diabetalert.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import web.mediscreen.diabetalert.model.Historic;

@FeignClient(value = "microservice-historic", url = "http://localhost:8082")
public interface HistoryFeign {
    
    @GetMapping(value = "/historic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Historic> getPatientById(@PathVariable("id") int id);
}
