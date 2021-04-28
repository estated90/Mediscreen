package web.historic.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

import web.historic.dto.HistoricDto;
import web.historic.model.Historic;

public class HistoricUtils {

	private HistoricUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static Historic convertDtoToHistoric(HistoricDto historicDto) {
		var mapper = DozerBeanMapperBuilder.buildDefault();
		return mapper.map(historicDto, Historic.class);
	}



}
