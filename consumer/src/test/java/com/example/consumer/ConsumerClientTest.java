package com.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureWireMock(port = 8080)
@AutoConfigureJsonTesters
public class ConsumerClientTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ReservationClient client;

    private final Reservation first = new Reservation("1", "MARIO");
    private final Reservation second = new Reservation("1", "JOSH");

    @Before
    public void before() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(Arrays.asList(first, second));

        WireMock.stubFor(WireMock.get("/reservations")
                .willReturn(WireMock
                        .aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(json)
                ));
    }

    @Test
    public void testShouldRetrieveAllReservations() {
        StepVerifier.create(client.getAllReservations())
                .expectNext(first)
                .expectNext(second)
                .verifyComplete();
    }
}
