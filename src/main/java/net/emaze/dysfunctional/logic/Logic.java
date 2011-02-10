package net.emaze.dysfunctional.logic;

/**
 *
 * @author rferranti
 */
public abstract class Logic {

    public static <T> CompositePredicate<T> and(Predicate<T>... predicates) {
        final CompositePredicate<T> pred = new AllMatchingPredicate<T>();
        for (Predicate<T> curr : predicates) {
            pred.add(curr);
        }
        return pred;
    }

    public static <T1, T2> CompositeBinaryPredicate<T1, T2> and(BinaryPredicate<T1, T2>... predicates) {
        final CompositeBinaryPredicate<T1, T2> pred = new AllMatchingBinaryPredicate<T1, T2>();
        for (BinaryPredicate<T1, T2> curr : predicates) {
            pred.add(curr);
        }
        return pred;
    }

    public static <T1, T2, T3> CompositeTernaryPredicate<T1, T2, T3> and(TernaryPredicate<T1, T2, T3>... predicates) {
        final CompositeTernaryPredicate<T1, T2, T3> pred = new AllMatchingTernaryPredicate<T1, T2, T3>();
        for (TernaryPredicate<T1, T2, T3> curr : predicates) {
            pred.add(curr);
        }
        return pred;
    }

    public static <T> CompositePredicate<T> or(Predicate<T>... predicates) {
        final CompositePredicate<T> pred = new FirstMatchingPredicate<T>();
        for (Predicate<T> curr : predicates) {
            pred.add(curr);
        }
        return pred;
    }

    public static <T1, T2> CompositeBinaryPredicate<T1, T2> or(BinaryPredicate<T1, T2>... predicates) {
        final CompositeBinaryPredicate<T1, T2> pred = new FirstMatchingBinaryPredicate<T1, T2>();
        for (BinaryPredicate<T1, T2> curr : predicates) {
            pred.add(curr);
        }
        return pred;
    }

    public static <T1, T2, T3> CompositeTernaryPredicate<T1, T2, T3> or(TernaryPredicate<T1, T2, T3>... predicates) {
        final CompositeTernaryPredicate<T1, T2, T3> pred = new FirstMatchingTernaryPredicate<T1, T2, T3>();
        for (TernaryPredicate<T1, T2, T3> curr : predicates) {
            pred.add(curr);
        }
        return pred;
    }

    public static <T> Predicate<T> not(Predicate<T> pred) {
        return new Negator<T>(pred);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> not(BinaryPredicate<T1, T2> pred) {
        return new BinaryNegator<T1, T2>(pred);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> not(TernaryPredicate<T1, T2, T3> pred) {
        return new TernaryNegator<T1, T2, T3>(pred);
    }

    public static <T> Predicate<T> always() {
        return new Always<T>();
    }

    public static <T1, T2> BinaryPredicate<T1, T2> always2() {
        return new BinaryAlways<T1, T2>();
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> always3() {
        return new TernaryAlways<T1, T2, T3>();
    }

    public static <T> Predicate<T> never() {
        return new Never<T>();
    }

    public static <T1, T2> BinaryPredicate<T1, T2> never2() {
        return new BinaryNever<T1, T2>();
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> never3() {
        return new TernaryNever<T1, T2, T3>();
    }
}
