package com.substring.irctc.service;

import com.substring.irctc.dto.BookingRequest;
import com.substring.irctc.dto.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);

}
