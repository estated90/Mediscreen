package web.mediscreen.historic.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

import web.mediscreen.historic.dto.HistoricDto;
import web.mediscreen.historic.model.Historic;

/**
 * @author Nicolas
 *
 */
public class HistoricUtils {

	/**
	 * 
	 */
	private HistoricUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @param historicDto The Dto to convert
	 * @return the historic converted
	 */
	public static Historic convertDtoToHistoric(HistoricDto historicDto) {
		var mapper = DozerBeanMapperBuilder.buildDefault();
		return mapper.map(historicDto, Historic.class);
	}



}
