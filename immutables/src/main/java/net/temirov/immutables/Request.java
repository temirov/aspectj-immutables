package net.temirov.immutables;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface Request {

    @Value.Default
    default String requestId() {
        return UUID.randomUUID().toString();
    }

    /**
     * A message to pass around
     */
    String message();
}
