package web.historic.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import web.historic.model.Historic;

@Repository
public interface HistoricRepositories extends MongoRepository<Historic, Integer> {

    @Query("{'patId':?0}")
    List<Historic> findByPatientId(int id);

}