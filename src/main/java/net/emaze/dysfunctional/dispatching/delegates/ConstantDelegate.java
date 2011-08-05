package net.emaze.dysfunctional.dispatching.delegates;

public class ConstantDelegate<R, T> implements Delegate<R, T> {

    private final R constant;

    public ConstantDelegate(R constant) {
        this.constant = constant;
    }
    
    @Override
    public R perform(T t) {
        return constant;
    }
}
