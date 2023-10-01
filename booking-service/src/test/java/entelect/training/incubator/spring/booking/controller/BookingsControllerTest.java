package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingsControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingsController bookingsController;

    @Test
    void testCreateBooking_returnsCreatedResponseWithBooking() {
        Booking requestBooking = mock(Booking.class);
        Booking createdBooking = mock(Booking.class);
        when(bookingService.createBooking(requestBooking)).thenReturn(createdBooking);

        ResponseEntity<?> response = bookingsController.createBooking(requestBooking);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdBooking, response.getBody());
    }

    @Test
    void testGetCustomerById_BookingFound_ReturnsOkResponseWithBooking() {
        Integer id = 99;
        Booking createdBooking = mock(Booking.class);
        when(bookingService.getBooking(id)).thenReturn(createdBooking);

        ResponseEntity<?> response = bookingsController.getCustomerById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdBooking, response.getBody());
    }

    @Test
    void testGetCustomerById_BookingNotFound_ReturnsNoContentResponse() {
        Integer id = 99;
        when(bookingService.getBooking(id)).thenReturn(null);

        ResponseEntity<?> response = bookingsController.getCustomerById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}