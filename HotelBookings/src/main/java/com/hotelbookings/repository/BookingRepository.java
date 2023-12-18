package com.hotelbookings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbookings.model.BookedRoom;

@Repository
public interface BookingRepository extends JpaRepository<BookedRoom, Long>  {

	void deleteById(Long bookingId);

	List<BookedRoom> findAll();

//	List<BookedRoom> findByRoomId();
//
//	List<BookedRoom> findByRoomId(Long roomId);

	BookedRoom findByBookingConfirmationCode(String confirmationCode);

	List<BookedRoom> findByRoomId(Long roomId);

	
}
