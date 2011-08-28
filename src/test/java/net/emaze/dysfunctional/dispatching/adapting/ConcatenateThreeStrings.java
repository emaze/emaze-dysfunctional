package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

public class ConcatenateThreeStrings implements TernaryDelegate<String, String, String, String> {

    @Override
    public String perform(String first, String second, String third) {
        return first + second + third;
    }
}
