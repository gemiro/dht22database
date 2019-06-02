package home.iot.dht22.database;

import home.iot.dht22.database.model.Temperature;
import home.iot.dht22.database.service.TemperatureService;
import io.nats.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class DatabaseConfiguration {

    @Autowired
    private TemperatureService temperatureService;

    @Bean
    public Connection natsConnection() throws IOException, InterruptedException {
        Options o = new Options.Builder().server("nats://192.168.1.205:4222").maxReconnects(-1).build();
        return Nats.connect(o);
    }

    @Bean
    CommandLineRunner commandLineRunner(Connection connection){
        return args->{
            connection.createDispatcher((msg) -> {
                String response = new String(msg.getData(), StandardCharsets.UTF_8);
                log.debug("Received Temperature:"+response);
                Temperature t = new Temperature();
                t.setTemperature(response);
                temperatureService.createTemperature(t).subscribe();
            }).subscribe("temperature");

            connection.createDispatcher((msg) -> {
                String response = new String(msg.getData(), StandardCharsets.UTF_8);
                log.debug("Received Temperature:"+response);
                Temperature t = new Temperature();
                t.setTemperature(response);
                temperatureService.createTemperature(t).subscribe();
            }).subscribe("humidity");

        };
    }
}
