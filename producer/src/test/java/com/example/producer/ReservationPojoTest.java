package com.example.producer;

import org.assertj.core.api.Assertions;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
public class ReservationPojoTest {

	@Test
	public void create() throws Exception {

		Reservation reservation = new Reservation("1", "Jane");
		Assert.assertEquals(reservation.getReservationName(), "Jane");
		Assert.assertEquals(reservation.getId(), "1");
		Assert.assertTrue("the first character should be 'J'", reservation.getReservationName().charAt(0) == 'J');

		Assert.assertThat(reservation.getReservationName(), new BaseMatcher<String>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("first character should be 'J'");
			}

			@Override
			public boolean matches(Object item) {
				String sItem = String.class.cast(item);
				return sItem.charAt(0) == 'J';
			}
		});

		Assertions.assertThat(reservation.getReservationName()).isEqualToIgnoringCase("jane");
		Assertions.assertThat(reservation.getId()).isNotBlank();
	}

}
