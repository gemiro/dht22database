package home.iot.dht22.database.repository;

import home.iot.dht22.database.model.Temperature;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface TemperatureRepository extends ReactiveMongoRepository<Temperature,String> {
    Flux<Temperature> findAllByCreatedDateAfter(Date createdDate);
}
