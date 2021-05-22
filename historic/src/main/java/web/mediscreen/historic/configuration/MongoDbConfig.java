package web.mediscreen.historic.configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import web.mediscreen.historic.repositories.HistoricRepositories;

@EnableMongoRepositories(basePackageClasses = HistoricRepositories.class)
public class MongoDbConfig {

}
