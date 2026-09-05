package ecommerce.utility;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private final AtomicLong counter;

    public IdGenerator(long initialValue) {
        if (initialValue < 0) {
            throw new IllegalArgumentException(
                    "Initial value cannot be negative"
            );
        }

        this.counter = new AtomicLong(initialValue);
    }

    public long nextId() {
        return counter.incrementAndGet();
    }

    public long currentId() {
        return counter.get();
    }
}