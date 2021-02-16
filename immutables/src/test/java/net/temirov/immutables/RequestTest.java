package net.temirov.immutables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {

    @Test
    void testRequestMessage() {
        String message = "message";

        Request immutableRequest = ImmutableRequest
                .builder()
                .message(message)
                .build();

        assertEquals(message, immutableRequest.message());
    }
}
