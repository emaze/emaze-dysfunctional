package net.emaze.dysfunctional.numbers.policies;

public interface MultiplyPolicy<R, T1, T2> {

    R multiply(T1 lhs, T2 rhs);
}
