package com.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
	ids = "com.example:producer:+:8080",
	stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@AutoConfigureWireMock(port = 8080)
//@AutoConfigureJsonTesters
public class ReservationClientTest {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ReservationClient client;

	private final Reservation first = new Reservation("1", "A");
	private final Reservation second = new Reservation("2", "B");

	@Before
	public void before() throws JsonProcessingException {

/*
		String json = objectMapper.writeValueAsString(
			Arrays.asList(first, second));

		WireMock.stubFor(WireMock.get("/reservations")
			.willReturn(WireMock
				.aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.withBody(json)
			));
*/

	}

	@Test
	public void testShouldRetrieveAllReservations() {
		StepVerifier.create(client.getAllReservations())
			.expectNext(first)
			.expectNext(second)
			.verifyComplete();
	}
}
