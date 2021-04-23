package web.historic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;
    private int seq;
    
    public DatabaseSequence() {}
    
    /**
     * @return the seq
     */
    public int getSeq() {
        return seq;
    }  

}
