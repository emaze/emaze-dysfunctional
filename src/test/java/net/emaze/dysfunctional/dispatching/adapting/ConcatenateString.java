package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

public class ConcatenateString implements BinaryDelegate<String, String, String> {

    @Override
    public String perform(String former, String latter) {
        return former + latter;
    }
}
