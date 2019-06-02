package home.iot.dht22.database.controller;

import home.iot.dht22.database.model.Humidity;
import home.iot.dht22.database.model.Temperature;
import home.iot.dht22.database.service.HumidityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/v1/dht22/humidity")
public class HumidityController {
    @Autowired
    private HumidityService humidityService;


    @GetMapping
    public Flux<Humidity> findAll() {
        log.debug("findAll Temperature");
        return humidityService.findAll();
    }

    @GetMapping("/average")
    public Mono<Float> findAllByCreatedDateAfter(
            @RequestParam @DateTimeFormat(pattern = "yyyyMMddHHmmss") Date from
    ) {
        return MathFlux.averageFloat(humidityService.findAllByCreatedDateAfter(from),
                humidity -> Float.parseFloat(humidity.getHumidity()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Humidity> create(@RequestBody Humidity humidity) {
        return humidityService.createHumidity(humidity);
    }

}
