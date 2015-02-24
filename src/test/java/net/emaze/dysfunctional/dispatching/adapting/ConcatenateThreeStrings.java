package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

public class ConcatenateThreeStrings implements TriFunction<String, String, String, String> {

    @Override
    public String apply(String first, String second, String third) {
        return first + second + third;
    }
}
