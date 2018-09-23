package com.example.producer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProducerDocumentTests {

	private final Reservation first = new Reservation(null, "MARIO");
	private final Reservation second = new Reservation(null, "JOSH");

	@Autowired
	ReactiveMongoTemplate template;

	@Test
	public void testShouldDocumentStoreRetrieve() {
		StepVerifier.create(
			Flux.just(first, second)
				.flatMap(template::save)
		)
			.expectNext(first)
			.expectNext(second)
			.verifyComplete();
	}


}
