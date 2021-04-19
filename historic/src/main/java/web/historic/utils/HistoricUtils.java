package web.historic.utils;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import web.historic.dto.HistoricDto;
import web.historic.model.Historic;

@Service
public class HistoricUtils {

    public static Historic convertDtoToHistoric(HistoricDto historicDto) {
	Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	return mapper.map(historicDto, Historic.class);
    }

}
