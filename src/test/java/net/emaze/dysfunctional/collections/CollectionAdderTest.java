package net.emaze.dysfunctional.collections;

import java.util.ArrayList;
import java.util.List;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CollectionAdderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingCollecitonAdderWithNullCollectionYieldsException() {
        new CollectionAdder<O>(null);
    }

    @Test
    public void canAddToCollection() {
        final List<O> bucket = new ArrayList<O>();
        new CollectionAdder<O>(bucket).apply(O.ONE);
        Assert.assertEquals(1, bucket.size());
    }
}
