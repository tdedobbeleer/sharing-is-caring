package be.tdedobbeleer.sharing_is_caring.clients;

import be.tdedobbeleer.sharing_is_caring.pojo.HaEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HomeAssistantWebClient implements HomeAssistantClient {
    private final WebClient client;
    private final String haToken;
    public HomeAssistantWebClient(@Value( "${ha.url}" ) String url,  @Value( "${ha.token}" ) String token) {
        this.haToken = token;
        client = WebClient.create(url);
    }

    public Mono<Integer> getEnergyToGrid() {
        return client.get()
                .uri("/states/sensor.p1_meter_power")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + haToken )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(HaEntity.class)
                .map(e -> Double.parseDouble(e.getState()))
                .map(e -> {
                    if (e.intValue() > 0) return 0;
                    else return Math.abs(e.intValue());
                });
    }
}
