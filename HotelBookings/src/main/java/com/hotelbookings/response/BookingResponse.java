package com.hotelbookings.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {


	private Long bookingId;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private String guestFullName;
	
	private String guestEmail;
	
	private int NumOfAdults;
	
	private int NumOfChildren;

	private int totalNumOfGuest;
	
	private String bookingConfirmationCode;
	
	
	public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate,
			String guestFullName, String guestEmail, int totalNumOfGuest, int NumOfChildren, int NumOfAdults, String bookingConfirmationCode, RoomResponse room) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookingConfirmationCode = bookingConfirmationCode;
		this.guestFullName = guestFullName;
		this.guestEmail = guestEmail;
		this.NumOfAdults = NumOfAdults;
		this.NumOfChildren = NumOfChildren;
		this.totalNumOfGuest = totalNumOfGuest;
	}
	

}
