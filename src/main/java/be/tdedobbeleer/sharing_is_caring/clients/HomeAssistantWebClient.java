package be.tdedobbeleer.sharing_is_caring.clients;

import be.tdedobbeleer.sharing_is_caring.entities.HaEntity;
import be.tdedobbeleer.sharing_is_caring.pojo.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HomeAssistantWebClient implements HomeAssistantClient {
    private final RestClient client;
    private final String haToken;
    public HomeAssistantWebClient(@Value( "${ha.url}" ) String url,  @Value( "${ha.token}" ) String token) {
        this.haToken = token;
        client = RestClient.create(url);
    }

    public State getEnergyToGrid() {
        try {
            HaEntity e = client.get()
                    .uri("/states/sensor.p1_meter_power")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + haToken)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().toEntity(HaEntity.class)
                    .getBody();
            double state = Double.parseDouble(e.getState());
            State s = new State();
            s.setDescription("W");
            s.setValue(state > 0 ? String.valueOf(0) : String.valueOf(Math.abs(state)));
            return s;
        }
        catch (Exception e) {
            return new State("Unavailable", "");
        }
    }
}
