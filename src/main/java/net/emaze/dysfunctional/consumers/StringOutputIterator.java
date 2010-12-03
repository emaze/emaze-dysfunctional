package net.emaze.dysfunctional.consumers;

/**
 *
 * @author rferranti
 */
public class StringOutputIterator implements OutputIterator<String> {

    private final StringBuilder res = new StringBuilder();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void next(String element) {
        res.append(element);
    }

    @Override
    public String toString() {
        return res.toString();
    }

    
}
