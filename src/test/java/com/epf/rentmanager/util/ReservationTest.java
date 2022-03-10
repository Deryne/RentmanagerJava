package com.epf.rentmanager.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.epf.rentmanager.model.Reservation;

public class ReservationTest {
	@Test
    void Duree_should_return_true_when_reservation_is_too_long() {
        Reservation legalReservation = new Reservation(1 , 1, 1, LocalDate.parse("2000-03-01"),
                LocalDate.parse("2000-03-11"));
        assertTrue(legalReservation.ResaTropLongue());
    }

    @Test
    void Duree_should_return_false_when_reservation_is_correct() {
        Reservation legalReservation = new Reservation(1 ,1, 1, LocalDate.parse("2000-03-01"),
                LocalDate.parse("2000-03-06"));
        assertFalse(legalReservation.ResaTropLongue());
    }

}
