package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.consumers.ConsumeIntoCollection;
import net.emaze.dysfunctional.consumers.FirstElement;
import net.emaze.dysfunctional.consumers.LastElement;
import net.emaze.dysfunctional.consumers.MaybeFirstElement;
import net.emaze.dysfunctional.consumers.MaybeLastElement;
import net.emaze.dysfunctional.consumers.MaybeOneElement;
import net.emaze.dysfunctional.consumers.OneElement;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti, fdegrassi
 */
public abstract class Searches {

    /**
     * Searches the iterator, consuming it, yielding every value matching the
     * predicate.
     *
     * @param <E> the element type
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @return a list containing matching elements
     */
    public static <E> List<E> search(Iterator<E> iterator, Predicate<E> predicate) {
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the iterator, consuming it, adding every value matching the
     * predicate to the passed collection.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterator the iterator to be searched
     * @param collection the collection to be filled with matching elements
     * @param predicate the predicate to be applied to each element
     * @return the passed collection, filled with matching elements
     */
    public static <C extends Collection<E>, E> C search(Iterator<E> iterator, C collection, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(new ConstantProvider<C>(collection));
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the iterator, consuming it, adding every value matching the
     * predicate to the collection yielded by the passed provider.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterator the iterator to be searched
     * @param provider the provider of the resulting collection
     * @param predicate the predicate to be applied to each element
     * @return the collection provided by the passed provider, filled with
     * matching elements
     */
    public static <C extends Collection<E>, E> C search(Iterator<E> iterator, Provider<C> provider, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(provider);
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the iterable, yielding every value matching the predicate.
     *
     * @param <E> the element type
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @return a list containing matching elements
     */
    public static <E> List<E> search(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot search a null iterable");
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the iterable, adding every value matching the predicate to the
     * passed collection.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterable the iterable to be searched
     * @param collection the collection to be filled with matching elements
     * @param predicate the predicate to be applied to each element
     * @return the passed collection, filled with matching elements
     */
    public static <C extends Collection<E>, E> C search(Iterable<E> iterable, C collection, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot search a null iterable");
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(new ConstantProvider<C>(collection));
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the iterable, adding every value matching the predicate to the
     * collection yielded by the passed provider.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterable the iterable to be searched
     * @param provider the provider of the resulting collection
     * @param predicate the predicate to be applied to each element
     * @return the collection provided by the passed provider, filled with
     * matching elements
     */
    public static <C extends Collection<E>, E> C search(Iterable<E> iterable, Provider<C> provider, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot search a null iterable");
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(provider);
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the array, adding every value matching the predicate to the
     * passed collection.
     *
     * @param <E> the element type
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @return a list containing matching elements
     */
    public static <E> List<E> search(E[] array, Predicate<E> predicate) {
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the array, adding every value matching the predicate to the
     * passed collection.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param array the array to be searched
     * @param collection the collection to be filled with matching elements
     * @param predicate the predicate to be applied to each element
     * @return the passed collection, filled with matching elements
     */
    public static <C extends Collection<E>, E> C search(E[] array, C collection, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(new ConstantProvider<C>(collection));
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the array, adding every value matching the predicate to the
     * collection yielded by the passed provider.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param array the array to be searched
     * @param provider the provider of the resulting collection
     * @param predicate the predicate to be applied to each element
     * @return the collection provided by the passed provider, filled with
     * matching elements
     */
    public static <C extends Collection<E>, E> C search(E[] array, Provider<C> provider, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(provider);
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return consumer.apply(filtered);
    }

    /**
     * Searches the iterator, consuming it, yielding every value matching the
     * predicate. An IllegalStateException is thrown if no element matches.
     *
     * @param <E> the element type
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return a list containing matching elements
     */
    public static <E> List<E> find(Iterator<E> iterator, Predicate<E> predicate) {
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        final ArrayList<E> found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the iterator, consuming it, adding every value matching the
     * predicate to the passed collection. An IllegalStateException is thrown if
     * no element matches.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterator the iterator to be searched
     * @param collection the collection to be filled with matching elements
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the passed collection, filled with matching elements
     */
    public static <C extends Collection<E>, E> C find(Iterator<E> iterator, C collection, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(new ConstantProvider<C>(collection));
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        C found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the iterator, consuming it, adding every value matching the
     * predicate to the collection yielded by the passed provider. An
     * IllegalStateException is thrown if no element matches.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterator the iterator to be searched
     * @param provider the provider of the resulting collection
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the collection provided by the passed provider, filled with
     * matching elements
     */
    public static <C extends Collection<E>, E> C find(Iterator<E> iterator, Provider<C> provider, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(provider);
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        final C found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the iterable, yielding every value matching the predicate. An
     * IllegalStateException is thrown if no element matches.
     *
     * @param <E> the element type
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return a list containing matching elements
     */
    public static <E> List<E> find(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot search a null iterable");
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        final ArrayList<E> found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the iterable, adding every value matching the predicate to the
     * passed collection. An IllegalStateException is thrown if no element
     * matches.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterable the iterable to be searched
     * @param collection the collection to be filled with matching elements
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the passed collection, filled with matching elements
     */
    public static <C extends Collection<E>, E> C find(Iterable<E> iterable, C collection, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot search a null iterable");
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(new ConstantProvider<C>(collection));
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        final C found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the iterable, adding every value matching the predicate to the
     * collection yielded by the passed provider. An IllegalStateException is
     * thrown if no element matches.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param iterable the iterable to be searched
     * @param provider the provider of the resulting collection
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the collection provided by the passed provider, filled with
     * matching elements
     */
    public static <C extends Collection<E>, E> C find(Iterable<E> iterable, Provider<C> provider, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot search a null iterable");
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(provider);
        final FilteringIterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        final C found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the array, yielding every value matching the predicate. An
     * IllegalStateException is thrown if no element matches.
     *
     * @param <E> the element type
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return a list containing matching elements
     */
    public static <E> List<E> find(E[] array, Predicate<E> predicate) {
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        final ArrayList<E> found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the array, adding every value matching the predicate to the
     * passed collection. An IllegalStateException is thrown if no element
     * matches.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param array the array to be searched
     * @param collection the collection to be filled with matching elements
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the passed collection, filled with matching elements
     */
    public static <C extends Collection<E>, E> C find(E[] array, C collection, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(new ConstantProvider<C>(collection));
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        final C found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the array, adding every value matching the predicate to the
     * collection yielded by the passed provider. An IllegalStateException is
     * thrown if no element matches.
     *
     * @param <C> the collection type
     * @param <E> the element type
     * @param array the array to be searched
     * @param provider the provider of the resulting collection
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the collection provided by the passed provider, filled with
     * matching elements
     */
    public static <C extends Collection<E>, E> C find(E[] array, Provider<C> provider, Predicate<E> predicate) {
        final Function<Iterator<E>, C> consumer = new ConsumeIntoCollection<>(provider);
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        final C found = consumer.apply(filtered);
        dbc.precondition(!found.isEmpty(), "no element matched");
        return found;
    }

    /**
     * Searches the first matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchFirst(E[] array, Predicate<E> predicate) {
        final FilteringIterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Searches the first matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchFirst(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Searches the first matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchFirst(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot searchFirst with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Searches the first matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findFirst(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Searches the first matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findFirst(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot findFirst with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Searches the first matching element returning it.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findFirst(E[] array, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Searches the only matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return new MaybeOneElement<E>().apply(filtered);
    }

    /**
     * Searches the only matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot searchOne with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return new MaybeOneElement<E>().apply(filtered);
    }

    /**
     * Searches the only matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(E[] array, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return new MaybeOneElement<E>().apply(filtered);
    }

    /**
     * Searches the only matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return new OneElement<E>().apply(filtered);
    }

    /**
     * Searches the only matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot findOne with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return new OneElement<E>().apply(filtered);
    }

    /**
     * Searches the only matching element returning it.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(E[] array, Predicate<E> predicate) {
        final Iterator<E> filtered = Filtering.filter(new ArrayIterator<E>(array), predicate);
        return new OneElement<E>().apply(filtered);
    }

    /**
     * Searches the last matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the last element found or nothing
     */
    public static <E> Maybe<E> searchLast(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return new MaybeLastElement<E>().apply(filtered);
    }

    /**
     * Searches the last matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the last element found or nothing
     */
    public static <E> Maybe<E> searchLast(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot searchLast with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return new MaybeLastElement<E>().apply(filtered);
    }

    /**
     * Searches the last matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the last element found or nothing
     */
    public static <E> Maybe<E> searchLast(E[] array, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return new MaybeLastElement<E>().apply(filtered);
    }

    /**
     * Searches the last matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the last element found
     */
    public static <E> E findLast(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, predicate);
        return new LastElement<E>().apply(filtered);
    }

    /**
     * Searches the last matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the last element found
     */
    public static <E> E findLast(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot searchLast with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), predicate);
        return new LastElement<E>().apply(filtered);
    }

    /**
     * Searches the last matching element returning it.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the last element found
     */
    public static <E> E findLast(E[] array, Predicate<E> predicate) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), predicate);
        return new LastElement<E>().apply(filtered);
    }
}