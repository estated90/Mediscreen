package web.mediscreen.historic.configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import web.mediscreen.historic.repositories.HistoricRepositories;

/**
 * @author Nicolas
 * <p>Configuration for use of mongo Db</p>
 *
 */
@EnableMongoRepositories(basePackageClasses = HistoricRepositories.class)
public class MongoDbConfig {

}
