package com.bharath.flightservices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReservationRequest {

	private int flightId;
	private String passengerFirstName;
	private String passengerLastName;
	private String passengerMiddleName;
	private String passengerEmail;
	private String passengerPhone;
	private String cardNumber;
	private String expirationDate;
	private String securityCode;
}
