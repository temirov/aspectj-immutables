package net.temirov.mixedservice.immutables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {

    @Test
    void testRequestMessage() {
        String message = "test";
        Request request = ImmutableRequest
                .builder()
                .message(message)
                .build();

        assertEquals(message, request.message());
    }
}
