package com.example.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/

@Configuration
public class ReservationRestConfiguration {

	@Bean
	RouterFunction<ServerResponse> routes (ReservationRepository rr ){
		return route(GET("/reservations"), r -> ok().body(rr.findAll(), Reservation.class)) ;
	}
}
