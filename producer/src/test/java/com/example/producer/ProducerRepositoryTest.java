package com.example.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProducerRepositoryTest {

	@Autowired
	ReservationRepository repository;

	private final Reservation first = new Reservation(null, "MARIO");
	private final Reservation second = new Reservation(null, "JOSH");

	@Test
	public void testShouldRepoFindAll() {

		Publisher<Reservation> composedFlux =
			Flux.just(first, second)
				.flatMap(repository::save)
				.thenMany(repository.findByName(first.getName()));

		StepVerifier.create(composedFlux)
			.expectNext(first)
			.verifyComplete();
	}
}
