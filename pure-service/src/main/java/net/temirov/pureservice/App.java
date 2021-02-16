package net.temirov.pureservice;

import net.temirov.immutables.Request;
import net.temirov.immutables.ImmutableRequest;
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
