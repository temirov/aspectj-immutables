package net.temirov.pureservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {

    @Test
    void main() {
        assertDoesNotThrow(() -> App.main(new String[0]));
    }
}
