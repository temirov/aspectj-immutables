package net.temirov.mixedservice;

import net.temirov.mixedservice.immutables.Request;
import net.temirov.mixedservice.immutables.ImmutableRequest;
import net.temirov.aspectj.LogExecutionTime;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        sayHi();
    }

    @LogExecutionTime
    private static void sayHi() {
        Request request = ImmutableRequest.builder()
                .message("Hello, World")
                .build();

        HiSayer hiSayer = new HiSayer(request.message());
        hiSayer.speak();
    }
}

