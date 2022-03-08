package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Vehicle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class VehicleTest {
	
    @Test
    void return_true_when_number_of_seat_is_correct() {
        // Given
        Vehicle CorrectVehicle = new Vehicle(1, "Citroen", 4);
        // Then
        assertTrue(CorrectVehicle.Nb_placesValide());
    }
    @Test
    void return_false_when_number_of_seat_is_correct() {
        // Given
    	Vehicle IncorrectVehicle  = new Vehicle(1, "Citroen", 1);
        // Then
        assertFalse(IncorrectVehicle.Nb_placesValide());
    }

}
