package com.hotelbookings.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelbookings.exception.InvalidBookingRequestException;
import com.hotelbookings.model.BookedRoom;
import com.hotelbookings.model.Room;
import com.hotelbookings.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
	
	private final BookingRepository bookingRepository;
	
	private final IRoomService roomService;
	
	@Override
	public List<BookedRoom> getAllBookings() {
		
		return bookingRepository.findAll();
	}

	@Override
	public void cancelBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
		
		return bookingRepository.findByRoomId(roomId);
	}

	@Override
	public String saveBooking(Long roomId, BookedRoom bookingRequest) {
		
		if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
			throw new InvalidBookingRequestException("Check-in date must come before check-out date");
		}
		Room room = roomService.getRoomById(roomId).get();
		List<BookedRoom> existingBookings = room.getBookings();
		boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingBookings);
		if(roomIsAvailable) {
			room.addBooking(bookingRequest);
			bookingRepository.save(bookingRequest);
		}else {
			throw new InvalidBookingRequestException("Sorry ,This room  is not available for the selected dates");
		}
		return bookingRequest.getBookingConfirmationCode();
	}


	@Override
	public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
		
		return bookingRepository.findByBookingConfirmationCode(confirmationCode);
	}
	
	

	private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
		return existingBookings.stream()
				.noneMatch(existingbooking ->
				bookingRequest.getCheckInDate().equals(existingbooking.getCheckInDate())
				|| bookingRequest.getCheckOutDate().isBefore(existingbooking.getCheckOutDate())
				|| (bookingRequest.getCheckInDate().isAfter(existingbooking.getCheckInDate()))
				&& bookingRequest.getCheckInDate().isBefore(existingbooking.getCheckOutDate())
				|| (bookingRequest.getCheckInDate().isBefore(existingbooking.getCheckInDate()))
				
				&& bookingRequest.getCheckOutDate().equals(existingbooking.getCheckOutDate())
				|| (bookingRequest.getCheckInDate().isBefore(existingbooking.getCheckInDate()))
				
				
				&& bookingRequest.getCheckOutDate().isBefore(existingbooking.getCheckOutDate())
				
				|| bookingRequest.getCheckInDate().equals(existingbooking.getCheckOutDate())
				&& (bookingRequest.getCheckOutDate().equals(existingbooking.getCheckInDate()))
				
				|| bookingRequest.getCheckInDate().equals(existingbooking.getCheckOutDate())
				&& (bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
			);
				
	}

	
}
