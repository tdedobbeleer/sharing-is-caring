package be.tdedobbeleer.sharing_is_caring.clients;

import be.tdedobbeleer.sharing_is_caring.pojo.State;

public interface HomeAssistantClient {
    State getEnergyToGrid();
}
