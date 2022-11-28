package vw.vorbereitung.warehouse.sequencegenerator.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * model class to generate own sequence for mongodb
 *
 * @author:  Marian Kowall
 */
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    @Getter
    private long seq;

    public DatabaseSequence() {
    }
}
