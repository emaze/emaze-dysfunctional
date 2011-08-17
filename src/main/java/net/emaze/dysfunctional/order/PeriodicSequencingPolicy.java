package net.emaze.dysfunctional.order;

public class PeriodicSequencingPolicy<T> implements SequencingPolicy<T> {

    private final SequencingPolicy<T> inner;
    private final T lower;
    private final T upper;

    public PeriodicSequencingPolicy(SequencingPolicy<T> inner, T lower, T upper) {
        this.inner = inner;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public T next(T element) {
        return element.equals(upper) ? lower : inner.next(element);
    }

    @Override
    public T prev(T element) {
        return element.equals(lower) ? upper : inner.prev(element);
    }
    
}
