package net.emaze.dysfunctional.numbers.policies;

public interface SubtractPolicy<R, T1, T2> {

    R subtract(T1 lhs, T2 rhs);
}
