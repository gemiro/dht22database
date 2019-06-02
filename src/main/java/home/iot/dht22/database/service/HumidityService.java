package home.iot.dht22.database.service;

import home.iot.dht22.database.model.Humidity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface HumidityService {
    Mono<Humidity> createHumidity(Humidity humidity);
    Flux<Humidity> findAll();
    Flux<Humidity> findAllByCreatedDateAfter(Date createdDate);
}
