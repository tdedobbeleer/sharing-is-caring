package be.tdedobbeleer.sharing_is_caring.controllers;

import be.tdedobbeleer.sharing_is_caring.clients.HomeAssistantClient;
import be.tdedobbeleer.sharing_is_caring.pojo.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;

@RestController
public class EnergyDataController {
    private final HomeAssistantClient client;

    public EnergyDataController(HomeAssistantClient homeAssistantClient) {
        this.client = homeAssistantClient;
    }

    @GetMapping("/energy/p1/grid/state")
    public SseEmitter streamEnergyToGridNumber() {
        SseEmitter emitter = new SseEmitter(0L);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                while (true) {
                    emitter.send(client.getEnergyToGrid());
                    Thread.sleep(2000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });

        return emitter;
    }
}
