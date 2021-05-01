package web.mediscreen.diabetalert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import web.mediscreen.diabetalert.model.Historic;
import web.mediscreen.diabetalert.proxy.HistoryFeign;

@Service
@ComponentScan({"web.mediscreen.diabetalert.proxy"})
public class DiabetService {

	@Autowired
	private HistoryFeign historicFeign;
	
	public String assessDiabetId(int id) {
		int patId = id;
		List<Historic> historic = historicFeign.getHistoricPatientById(patId);
		return evaluateRisk(historic, patId);
	}

	public String assessDiabetString(String familyName) {
		Optional<List<Historic>> historicOp = Optional.ofNullable(historicFeign.getHistoryWithName(familyName));
		List<Historic> historic = null;
		var patId = historicOp.filter(list-> !list.isEmpty()).map(list->list.get(0)).map(Historic::getPatId).orElse(0);
		if (historicOp.isPresent()) {
			historic = historicOp.get();
		}
		return evaluateRisk(historic, patId);
	}
	
	private String evaluateRisk(List<Historic> historics, int patId) {
		
		
		return null;
		
	}

}

