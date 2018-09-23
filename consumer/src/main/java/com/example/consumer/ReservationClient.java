package com.example.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ReservationClient {

    private final WebClient webClient;

    public ReservationClient(WebClient webClient, @Value("${service.uri:http://localhost:8080/reservations}") String su) {
        this.webClient = webClient;
        this.serviceURI = su;
    }

    private final String serviceURI;

    public Flux<Reservation> getAllReservations() {
        return webClient.get().uri(serviceURI)
                .retrieve()
                .bodyToFlux(Reservation.class);
    }
}
