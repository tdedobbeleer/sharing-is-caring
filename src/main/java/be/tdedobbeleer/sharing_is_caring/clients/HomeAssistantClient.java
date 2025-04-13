package be.tdedobbeleer.sharing_is_caring.clients;

import reactor.core.publisher.Mono;

public interface HomeAssistantClient {
    Mono<Integer> getEnergyToGrid();
}
