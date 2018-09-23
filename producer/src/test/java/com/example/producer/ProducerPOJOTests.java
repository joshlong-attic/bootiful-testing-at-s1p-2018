package com.example.producer;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class ProducerPOJOTests {


	@Test
	public void testShouldConstruct() {
		Reservation reservation = new Reservation("1", "mario");

		Assert.assertEquals(reservation.getId(), "1");
		Assert.assertEquals(reservation.getName(), "mario");

		Assert.assertThat(reservation.getId(), Matchers.notNullValue());
		Assert.assertThat(reservation.getName(), Matchers.equalToIgnoringCase("mario"));

		Assertions.assertThat(reservation.getId())
				.as("Has an ID")
				.isNotNull()
				.isEqualTo("1");

		Assertions.assertThat(reservation.getName())
				.as("Has a name")
				.isNotNull()
				.isNotBlank()
				.isEqualTo("mario");
	}
}
