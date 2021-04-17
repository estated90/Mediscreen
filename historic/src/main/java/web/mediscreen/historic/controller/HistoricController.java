package web.mediscreen.historic.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.mediscreen.historic.model.Historic;
import web.mediscreen.historic.service.HistoricService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("historic")
public class HistoricController {

    private static final Logger logger = LogManager.getLogger(HistoricController.class);
    @Autowired
    private HistoricService historicService;
    
    @GetMapping(value = "/all")
    public List<Historic> getAllHistoryPatient(@RequestParam int id){
	logger.info("Getting the history for patient {}", id);
	return historicService.getHistoryOfPatient(id);
    }
}
