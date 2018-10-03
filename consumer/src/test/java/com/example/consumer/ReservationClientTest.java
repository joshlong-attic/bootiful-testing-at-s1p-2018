package com.example.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
/*@AutoConfigureWireMock(port = 8080)
@AutoConfigureJsonTesters*/
@AutoConfigureStubRunner(
	ids = "com.example:producer:+:8080",
	stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
public class ReservationClientTest {
//
//	@Autowired
//	private ObjectMapper objectMapper;

	@Autowired
	private ReservationClient client;

	private final Reservation one = new Reservation("1", "Ai");
	private final Reservation two = new Reservation("2", "Zhang Wei");

	@Test
	public void getAllReservations() throws Exception {

/*
		String json = this.objectMapper.writeValueAsString(Arrays.asList(this.one, this.two));

		WireMock.stubFor(WireMock.get("/reservations")
			.willReturn(WireMock
				.aResponse()
				.withBody(json)
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			)
		);
*/

		StepVerifier
			.create(this.client.getAllReservations())
			.expectNext(this.one, this.two)
			.verifyComplete();

	}
}
