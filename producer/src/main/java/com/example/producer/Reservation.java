package com.example.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation {

	@Id
	private String id;
	private String reservationName;
}
