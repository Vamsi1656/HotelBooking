package com.hotelbookings.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbookings.exception.InvalidBookingRequestException;
import com.hotelbookings.model.BookedRoom;
import com.hotelbookings.model.Room;
import com.hotelbookings.response.BookingResponse;
import com.hotelbookings.response.RoomResponse;
import com.hotelbookings.service.IBookingService;
import com.hotelbookings.service.IRoomService;
import com.hotelbookings.service.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	
	private final IBookingService bookingService;
	
	private final IRoomService roomService;
	
	@GetMapping("/all-bookings")
	public ResponseEntity<List<BookingResponse>> getAllBookings(){
		
		List<BookedRoom> bookings = bookingService.getAllBookings();
		List<BookingResponse> bookingResponses = new ArrayList<>();
		for(BookedRoom booking : bookings) {
			BookingResponse bookingResponse = getBookingResponse(booking);
			bookingResponses.add(bookingResponse);
		}
		return ResponseEntity.ok(bookingResponses);
	}
	
	

	@GetMapping("/confirmation/{confirmationcode}")
	public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode){
		try {
			BookedRoom booking = bookingService.findByBookingConfirmationCode(confirmationCode);
			BookingResponse bookingResponse = getBookingResponse(booking);
			return ResponseEntity.ok(bookingResponse);
			
		}catch(ResourceNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping("/room/{roomId}/booking")
	public ResponseEntity<?> saveBooking(@PathVariable Long roomId,
										   @RequestBody BookedRoom bookingRequest){
		
		try {
			
			String confirmationCode = bookingService.saveBooking(roomId, bookingRequest);
			return ResponseEntity.ok("Room Booked Successfully ! Your Booking Confirmation code is:" + confirmationCode);
			
		}catch(InvalidBookingRequestException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/booking/{bookingId}/delete")
	public void cancelBooking(@PathVariable Long bookingId) {
		bookingService.cancelBooking(bookingId);
	}
	
	private BookingResponse getBookingResponse(BookedRoom booking) {
		Room theRoom = roomService.getRoomById(booking.getRoom().getId()).get();
		RoomResponse room = new RoomResponse(
				theRoom.getId(), 
				theRoom.getRoomType(), 
				theRoom.getRoomPrice());
		return new BookingResponse(
				booking.getBookingId(), booking.getCheckInDate(),
				booking.getCheckOutDate(), booking.getGuestFullName(),
				booking.getGuestEmail(), booking.getNumOfAdults(),
				booking.getNumOfChildren(), booking.getTotalNumOfGuest(),
				booking.getBookingConfirmationCode(), room);
	}

}
