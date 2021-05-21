package web.mediscreen.historic.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import web.mediscreen.historic.model.Historic;

/**
 * @author Nicolas
 *
 */
@Repository
public interface HistoricRepositories extends MongoRepository<Historic, Integer> {

    /**
     * @param id of the patient
     * @return the list of historic
     */
    @Query(value = "{patId:?0}", sort = "{createdAt : -1}")
    List<Historic> findByPatientId(int id);

    /**
     * @param patient as object
     * @return the list of historic
     */
    @Query(value = "{patient:?0}", sort = "{createdAt : -1}")
    List<Historic> findByName(String patient);

}
