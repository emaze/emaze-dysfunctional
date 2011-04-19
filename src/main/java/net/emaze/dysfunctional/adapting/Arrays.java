package net.emaze.dysfunctional.adapting;

/**
 *
 * @author rferranti
 */
public abstract class Arrays {

    public static Long[] box(long[] array) {
        final Long[] result = new Long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }

    public static long[] unbox(Long[] array) {
        final long[] result = new long[array.length];
        for (int i = 0; i != result.length; ++i) {
            result[i] = array[i];
        }
        return result;
    }
}
