package net.emaze.dysfunctional;

import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.Maps.Nested;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.MapTreeBuilder;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.order.ComparableComparator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MapsTest.Builder.class, MapsTest.NestedBuilder.class})
public class MapsTest {

    private static final Map<String, Object> A_MAP = new HashMap<String, Object>();

    public static class Builder {

        @Test
        public void canCreateBuilderFromMap() {
            final MapBuilder<String, Object> builder = Maps.from(A_MAP);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateBuilderFromProvider() {
            final Provider<Map<String, Object>> provider = Dispatching.compose(new Narrow<Map<String, Object>, HashMap<String, Object>>(), new HashMapFactory<String, Object>());
            final MapBuilder<String, Object> builder = Maps.from(provider);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyUnsortedBuilder() {
            final MapBuilder<String, Object> builder = Maps.unsorted();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptySortedBuilder() {
            final MapBuilder<String, Object> builder = Maps.sorted();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyOrderedBuilder() {
            final MapBuilder<String, Object> builder = Maps.ordered();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyOrderedWithComparatorBuilder() {
            final MapBuilder<String, Object> builder = Maps.ordered(new ComparableComparator<String>());
            Assert.assertNotNull(builder);
        }
    }

    public static class NestedBuilder {

        @Test
        public void canCreateBuilderFromProvider() {
            final Provider<Map<String, Object>> provider = Dispatching.compose(new Narrow<Map<String, Object>, HashMap<String, Object>>(), new HashMapFactory<String, Object>());
            final MapTreeBuilder<String> builder = Nested.from(provider);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyUnsortedBuilder() {
            final MapTreeBuilder<String> builder = Nested.unsorted();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptySortedBuilder() {
            final MapTreeBuilder<String> builder = Nested.sorted();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyOrderedBuilder() {
            final MapTreeBuilder<String> builder = Nested.ordered();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyOrderedWithComparatorBuilder() {
            final MapTreeBuilder<String> builder = Nested.ordered(new ComparableComparator<String>());
            Assert.assertNotNull(builder);
        }       
    }
}