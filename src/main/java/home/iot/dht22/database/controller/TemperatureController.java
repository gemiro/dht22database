package home.iot.dht22.database.controller;

import home.iot.dht22.database.model.Temperature;
import home.iot.dht22.database.service.TemperatureService;
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
@RequestMapping("/api/v1/dht22/temperature")
public class TemperatureController {
    @Autowired
    private TemperatureService temperatureService;


    @GetMapping
    public Flux<Temperature> findAll() {
        log.debug("findAll Temperature");
        return temperatureService.findAll();
    }

    @GetMapping("/average")
    public Mono<Float> findAllByCreatedDateAfter(
            @RequestParam @DateTimeFormat(pattern = "yyyyMMddHHmmss") Date from
    ) {
        return MathFlux.averageFloat(temperatureService.findAllByCreatedDateAfter(from),
                temperature -> Float.parseFloat(temperature.getTemperature()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Temperature> create(@RequestBody Temperature temperature) {
        log.debug("create Blog with blog : {}", temperature);
        return temperatureService.createTemperature(temperature);
    }

}
