package net.emaze.dysfunctional.numbers;

/**
 *
 * @author rferranti
 */
public class OverflowingInteger {

    private int value;
    private int limit;

    public OverflowingInteger(int limit) {
        this.limit = limit;
    }

    public int incrementAndGet() {
        value = (value + 1) % limit;
        return value;
    }

    public int getAndIncrement() {
        final int oldValue = value;
        value = (oldValue + 1) % limit;
        return oldValue;
    }

    public int get(){
        return value;
    }
}
