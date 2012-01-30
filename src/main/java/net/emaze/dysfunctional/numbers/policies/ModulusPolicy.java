package net.emaze.dysfunctional.numbers.policies;

public interface ModulusPolicy<R, T1, T2> {

    R modulus(T1 lhs, T2 modulus);
}
