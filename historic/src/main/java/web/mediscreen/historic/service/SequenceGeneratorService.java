package web.mediscreen.historic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import web.mediscreen.historic.model.DatabaseSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

/**
 * @author Nicolas
 *
 */
@Service
public class SequenceGeneratorService {

	private MongoOperations mongoOperations;

	/**
	 * @param mongoOperations
	 */
	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	/**
	 * @param seqName of the collection
	 * @return the next id to insert
	 */
	public int generateSequence(String seqName) {
		// get sequence number
		var query = new Query(Criteria.where("id").is(seqName));
		// update the sequence n°
		var update = new Update().inc("seq", 1);
		// modify in document
		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;

	}
}
