package home.iot.dht22.database.service;

import home.iot.dht22.database.model.Temperature;
import home.iot.dht22.database.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TemperatureServiceImpl implements TemperatureService{
    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public Mono<Temperature> createTemperature(Temperature temperature) {
        return temperatureRepository.insert(temperature);
    }

    @Override
    public Flux<Temperature> findAll() {
        return temperatureRepository.findAll();
    }

    @Override
    public Flux<Temperature> findAllByCreatedDateAfter(Date createdDate) {
        return temperatureRepository.findAllByCreatedDateAfter(createdDate);
    }
}
