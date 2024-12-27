package com.bharath.flightservices.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Passenger extends AbstractEntity{
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String phone;
}
