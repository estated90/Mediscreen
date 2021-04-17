package web.mediscreen.historic.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import web.mediscreen.historic.model.Historic;

@Repository
public interface HistoricRepositories extends MongoRepository<Historic, Integer> {

    @Query("{patient:'?0'}")
    List<Historic> findByPatientId(int id);

}
