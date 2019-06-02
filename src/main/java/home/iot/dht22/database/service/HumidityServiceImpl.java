package home.iot.dht22.database.service;

import home.iot.dht22.database.model.Humidity;
import home.iot.dht22.database.repository.HumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class HumidityServiceImpl implements HumidityService{
    @Autowired
    private HumidityRepository humidityRepository;

    @Override
    public Mono<Humidity> createHumidity(Humidity humidity) {
        return humidityRepository.insert(humidity);
    }

    @Override
    public Flux<Humidity> findAll() {
        return humidityRepository.findAll();
    }

    @Override
    public Flux<Humidity> findAllByCreatedDateAfter(Date createdDate) {
        return humidityRepository.findAllByCreatedDateAfter(createdDate);
    }
}
