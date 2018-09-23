package com.example.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@RunWith(SpringRunner.class)
@WebFluxTest
public class ProducerRestTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ReservationRepository reservationRepository;

	@Test
	public void getAllReservations() {

		Mockito.when(this.reservationRepository.findAll())
			.thenReturn(Flux.just(new Reservation(UUID.randomUUID().toString(), "MARIO")));
		
		this.webTestClient
			.get()
			.uri("/reservations")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBody()
			.jsonPath("@.[0].name").isEqualTo("MARIO");


	}

}
