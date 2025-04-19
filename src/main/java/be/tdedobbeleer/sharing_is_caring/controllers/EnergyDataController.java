package be.tdedobbeleer.sharing_is_caring.controllers;

import be.tdedobbeleer.sharing_is_caring.clients.HomeAssistantClient;
import be.tdedobbeleer.sharing_is_caring.pojo.State;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class EnergyDataController {
    private final HomeAssistantClient client;

    public EnergyDataController(HomeAssistantClient homeAssistantClient) {
        this.client = homeAssistantClient;
    }

    @GetMapping("/energy/p1/grid/state")
    public Flux<ServerSentEvent<State>> streamEnergyToGridNumber() {
        return Flux.interval(Duration.ofSeconds(2))
                .flatMap((i) -> client.getEnergyToGrid())
                .map(state -> ServerSentEvent.<State>builder()
                        .data(state)
                        .build());
    }
}
