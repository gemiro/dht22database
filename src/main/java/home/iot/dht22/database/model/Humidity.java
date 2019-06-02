package home.iot.dht22.database.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "temperature")
public class Humidity extends BaseEntity {
    private String humidity;
}
