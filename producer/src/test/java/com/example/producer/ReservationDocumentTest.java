package com.example.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@DataMongoTest
@RunWith(SpringRunner.class)
public class ReservationDocumentTest {

	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	private final Reservation one = new Reservation(null, "Mario");
	private final Reservation two = new Reservation(null, "Makani");

	@Test
	public void persist() throws Exception {


		Flux<Reservation> reservationFlux = Flux.just(this.one, this.two);
		Flux<Reservation> saved = reservationFlux.flatMap(this.reactiveMongoTemplate::save);

		Predicate<Reservation> p = reservation ->
			StringUtils.hasText(reservation.getReservationName()) &&
				StringUtils.hasText(reservation.getId());

		StepVerifier
			.create(saved)
			.expectNextMatches(p)
			.expectNextMatches(p)
			.verifyComplete();
	}

}
