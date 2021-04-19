package web.historic.configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import web.historic.repositories.HistoricRepositories;

@EnableMongoRepositories(basePackageClasses = HistoricRepositories.class)
public class MongoDbConfig {

}
