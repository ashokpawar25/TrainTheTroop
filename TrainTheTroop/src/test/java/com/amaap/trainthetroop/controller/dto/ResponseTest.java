package com.amaap.trainthetroop.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    void shouldCheckEqualityBasedOnStatusAndMessage() {
        // arrange
        Response response1 = new Response(HttpStatus.OK, "Equal message");
        Response response2 = new Response(HttpStatus.OK, "Equal message");
        Response response3 = new Response(HttpStatus.OK, "Different message");
        Response response4 = new Response(HttpStatus.BADREQUEST, "Equal message");
        Object object = new Object();

        // act & assert
        assertFalse(response1.equals(object));
        assertTrue(response1.equals(response1));
        assertTrue(response1.equals(response2));
        assertFalse(response1.equals(response3));
        assertFalse(response1.equals(response4));
        assertFalse(response1.equals(null));
    }
}