package com.bharath.flightservices.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.flightservices.dto.CreateReservationRequest;
import com.bharath.flightservices.dto.UpdateReservationRequest;
import com.bharath.flightservices.entities.Flight;
import com.bharath.flightservices.entities.Passenger;
import com.bharath.flightservices.entities.Reservation;
import com.bharath.flightservices.repos.FlightRepository;
import com.bharath.flightservices.repos.PassengerRepository;
import com.bharath.flightservices.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@GetMapping("/flights")
	public List<Flight> findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "MM-dd-yyyy") Date dateOfDeparture) {
		return flightRepository.findFlights(from, to, dateOfDeparture);
	}

	@GetMapping("/flights/{id}")
	public Flight findFlight(@PathVariable("id") int id) {
		return flightRepository.findById(id).get();
	}

	@PostMapping("/reservations")
	@Transactional
	public Reservation saveReservation(@RequestBody CreateReservationRequest request) {

		Optional<Flight> flight = flightRepository.findById(request.getFlightId());

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setMiddleName(request.getPassengerMiddleName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());

		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight.get());
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(Boolean.FALSE);

		return reservationRepository.save(reservation);
	}

	@GetMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") int id) {
		return reservationRepository.findById(id).get();
	}

	@PutMapping("/reservations")
	public Reservation updateReservation(@RequestBody UpdateReservationRequest request) {
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.isCheckIn());
		return reservationRepository.save(reservation);
	}
}
