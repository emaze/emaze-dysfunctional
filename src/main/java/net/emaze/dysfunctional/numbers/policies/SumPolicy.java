package net.emaze.dysfunctional.numbers.policies;

public interface SumPolicy<R, T1, T2> {

    R sum(T1 lhs, T2 rhs);
}
