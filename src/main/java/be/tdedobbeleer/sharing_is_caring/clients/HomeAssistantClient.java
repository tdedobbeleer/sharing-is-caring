package be.tdedobbeleer.sharing_is_caring.clients;

import be.tdedobbeleer.sharing_is_caring.pojo.State;
import reactor.core.publisher.Mono;

public interface HomeAssistantClient {
    State getEnergyToGrid();
}
