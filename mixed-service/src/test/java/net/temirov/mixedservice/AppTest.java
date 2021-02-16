package net.temirov.mixedservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void main() {
        assertDoesNotThrow(() -> App.main(new String[0]));
    }
}
