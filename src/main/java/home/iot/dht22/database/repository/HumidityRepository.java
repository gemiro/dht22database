package home.iot.dht22.database.repository;

import home.iot.dht22.database.model.Humidity;
import home.iot.dht22.database.model.Temperature;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface HumidityRepository extends ReactiveMongoRepository<Humidity,String> {
    Flux<Humidity> findAllByCreatedDateAfter(Date createdDate);
}
