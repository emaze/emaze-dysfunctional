package net.emaze.dysfunctional.numbers.policies;

public interface DividePolicy<R, T1, T2> {

    R divide(T1 lhs, T2 rhs);
}
