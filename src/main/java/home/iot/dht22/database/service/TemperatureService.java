package home.iot.dht22.database.service;

import home.iot.dht22.database.model.Temperature;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface TemperatureService {
    Mono<Temperature> createTemperature(Temperature temperature);
    Flux<Temperature> findAll();
    Flux<Temperature> findAllByCreatedDateAfter(Date createdDate);
}
