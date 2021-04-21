package web.historic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import web.historic.model.DatabaseSequence;

@Service
public class SequenceGeneratorService {

    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public int generateSequence(String seqName) {
	//get sequence number
        Query query = new Query(Criteria.where("id").is(seqName));
        //update the sequence n°
        Update update = new Update().inc("seq", 1);
        //modify in document
        DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DatabaseSequence.class);
	return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}
