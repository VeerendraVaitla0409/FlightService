package com.bharath.flightservices.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateReservationRequest {

	private int id;
	private boolean checkIn;
	private int numberOfBags;
}
