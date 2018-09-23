package com.example.producer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReservationRepository  extends ReactiveMongoRepository<Reservation, String> {
    Flux<Reservation> findByName(String name);
}
