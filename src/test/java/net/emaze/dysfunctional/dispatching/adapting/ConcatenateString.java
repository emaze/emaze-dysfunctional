package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiFunction;

public class ConcatenateString implements BiFunction<String, String, String> {

    @Override
    public String apply(String former, String latter) {
        return former + latter;
    }
}
