package net.emaze.dysfunctional.delegates;

public class ConcatenateThreeStrings implements TernaryDelegate<String, String, String, String> {

    @Override
    public String perform(String first, String second, String third) {
        return first + second + third;
    }
}
