package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testCreateBooking_SavesBookingInRepository() {
        Booking booking = mock(Booking.class);
        Booking savedBooking = mock(Booking.class);
        when(bookingRepository.save(booking)).thenReturn(savedBooking);

        Booking result = bookingService.createBooking(booking);

        assertEquals(savedBooking, result);
    }

    @Test
    void testGetBooking_RetrievesBookingFromRepository() {
        Integer id = 99;
        Booking savedBooking = mock(Booking.class);
        when(bookingRepository.findById(id)).thenReturn(Optional.of(savedBooking));

        Booking result = bookingService.getBooking(id);

        assertEquals(savedBooking, result);
    }

    @Test
    void testGetBooking_BookingNotFound_ReturnsNull() {
        Integer id = 99;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        Booking result = bookingService.getBooking(id);

        assertNull(result);
    }
}