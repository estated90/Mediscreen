package web.mediscreen.historic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import web.mediscreen.historic.model.Historic;

@Repository
public interface HistoricRepositories extends MongoRepository<Historic, Integer> {

}
