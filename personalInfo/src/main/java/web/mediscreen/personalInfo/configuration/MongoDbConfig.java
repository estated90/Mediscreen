package web.mediscreen.personalInfo.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDbConfig {

	public MongoClient connectToClient() {
		return new MongoClient("localhost", 27017);	
	}
	
	public MongoDatabase connectToDb(MongoClient mongoClient) {
		return mongoClient.getDatabase("Mediscreen");
	}
		
}
