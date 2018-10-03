package com.example.producer;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@RunWith(SpringRunner.class)
@Import(ReservationRestConfiguration.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	properties = "server.port=0"
)
public class BaseClass {

	@LocalServerPort
	private int port;

	@MockBean
	private ReservationRepository reservationRepository;

	private final Reservation one = new Reservation("1", "Ai");
	private final Reservation two = new Reservation("2", "Zhang Wei");

	@Before
	public void init() throws Exception {

		RestAssured.baseURI = "http://localhost:" + this.port;

		Mockito
			.when(this.reservationRepository.findAll())
			.thenReturn(Flux.just(this.one, this.two));

	}


}
