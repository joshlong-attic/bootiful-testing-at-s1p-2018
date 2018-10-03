package com.example.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@DataMongoTest
@RunWith(SpringRunner.class)
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository repository;

	private final Reservation one = new Reservation(null, "Francois");
	private final Reservation two = new Reservation(null, "Marie Jeanne");

	@Test
	public void getAllReservations() throws Exception {

		Flux<Reservation> saved =

			this.repository
				.deleteAll()
				.thenMany(Flux.just(this.one, this.two)
					.flatMap(this.repository::save));

		StepVerifier
			.create(saved.thenMany(this.repository.findByReservationName("Francois")))
			.expectNextMatches(r -> r.getReservationName().equalsIgnoreCase(this.one.getReservationName()))
			.verifyComplete();


	}
}
