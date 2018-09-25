package com.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringRunner.class)
@DataMongoTest
@Slf4j
public class ProducerRepositoryTest {

    @Autowired
    ReservationRepository repository;

    private final Reservation first = new Reservation(null, "MARIO");
    private final Reservation second = new Reservation(null, "JOSH");

    @Test
    public void testShouldRepoFindByName() {

        Publisher<Reservation> composedFlux =
                Flux.just(first, second)
                        .thenMany(repository.findByName(first.getName()));

        StepVerifier.create(composedFlux)
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("mario"))
                .verifyComplete();
    }

    @Test
    public void testShouldFindAll() {
        Publisher<Reservation> composedFlux =
                Flux.just(first, second)
                        .flatMap(repository::save)
                        .thenMany(repository.findAll());

        Collection<Reservation> reservations = new ArrayList<>();

        StepVerifier.create(composedFlux)
                .recordWith(() -> reservations)
                .expectNextCount(2)
                .expectNextSequence(reservations)
                .verifyComplete();
    }
}
