package be.tdedobbeleer.sharing_is_caring.controllers;

import be.tdedobbeleer.sharing_is_caring.clients.HomeAssistantClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EnergyDataController {
    private final HomeAssistantClient client;

    public EnergyDataController(HomeAssistantClient homeAssistantClient) {
        this.client = homeAssistantClient;
    }

    @GetMapping("/energy/p1/grid/state")
    public Mono<Integer> getEnergyToGridNumber() {
        return client.getEnergyToGrid();
    }
}
