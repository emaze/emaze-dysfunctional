package net.emaze.dysfunctional.delegates;

public class ConcatenateString implements BinaryDelegate<String, String, String> {

    @Override
    public String perform(String former, String latter) {
        return former + latter;
    }
}
