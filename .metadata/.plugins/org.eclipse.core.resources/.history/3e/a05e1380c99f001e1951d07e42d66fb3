package com.hotelbookings.service;

import java.util.List;

import com.hotelbookings.model.BookedRoom;



public interface IBookingService {
    void cancelBooking(Long bookingId);

    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

	List<BookedRoom> getAllBookings();

//    List<BookedRoom> getAllBookings();
//
//    List<BookedRoom> getBookingsByUserEmail(String email);
}