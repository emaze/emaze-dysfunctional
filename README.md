## Build and deploy

```
mvn clean package org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy -DaltDeploymentRepository=mvn-intranet::default::http://mvn.intranet/nexus/content/repositories/releases
```

## Changes

### 8.1.0-SNAPSHOT

#### Additions

Introduced `Collecting` façade to support additional collectors.

* `Collecting.flat` can be used to flat a `Stream` of `Iterable`s, as an example:
```
Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)).collect(Collecting.flat()); // yields [1, 2, 3, 4, 5]
```
* `Collecting.reverse` useful to invert the order of a finite stream.
```
Stream.of(1, 2, 3).collect(Collecting.reverse()); // yields [3, 2, 1]
```

Introduced `Strategies` façade with the following methods (:warning: still to test!) :
* `Function<T, R> firstMatch(Clause<T, R>... clauses)` to apply the first clause matching a condition
* `Function<T, Optional<R>>` maybeFirstMatch(Clause<T, R>... clauses)` to apply optionally the first clause matching a condition
* `Function<T, List<R>>` allMatches(Clause<T, R>... clauses)` to apply all clauses matching a condition
* also for binary functions

#### Refactors

Some better names:
* renamed `Multiplexing.batch` in `unchain` (`BatchingIterator` -> `UnchainIterator`)
* renamed `Multiplexing.unchain` in `unchainShortest` (`UnchainIterator` -> `UnchainShortestIterator`)
* renamed `Multiplexing.unchainWithExactChannelSize` in `unchainLongest` (`UnchainWithExactChannelSizeIterator` -> `UnchainLongestIterator`)

`Multiplexing.flatten` and `chain` accepts varargs.

### 8.0

#### Additions

Introduced the `Sequences` façade, in order to simplify the usage of sequential streams.
A `Sequence` has the following additional methods:
* Consumers: `toList`, `toSet`, `toMap`, `first`, `maybeFirst`, `one`, `maybeOne`, `last`, `maybeLast`, `nth`, `maybeNth`, `at`, `maybeAt`
* Filtering: `take`, `takeLast`, `takeAtMostLast`, `takeWhile`, `drop`, `dropWhile`, `slice`, `distinctBy`
* Multiplexing: `chain`
* Applications: `tap`

Added the following new methods:
* `Optional<LT> Either::left()` method to get the left part of the `Either`
* `Function<Either<LT, RT>, Either<LR, RR>> Eithers::lift(Function<LT, LR> left, Function<RT, RR> right)` to lift functions working on the components of an `Either` into a single function working on either
* `Function<Box<T>, Box<R>> Boxes::lift(Function<T, R> function)` to lift a function to another working on box values
* `Maybe<T> Maybes::toMaybe(Optional<T> optional)` to convert an `Optional` to a `Maybe`
* `Pair<T2, T1> Pair::flip()`
* `Triple<T3, T2, T1> Triple::flip()`
* `Triple<T2, T3, T1> Triple::rotateLeft()`
* `Triple<T3, T1, T2> Triple::rotateRight()`

#### Refactors

Replaced functional interfaces with built-in ones in package `java.util.function`:
* `Delegate` -> `Function`
* `BinaryDelegate` -> `BiFunction`
* `Provider` -> `Supplier`
* `Predicate` -> `Predicate`
* `BinaryPredicate` -> `BiPredicate`
* `Action` -> `Consumer`
* `BinaryAction` -> `BiConsumer`
* `Proposition` -> `BooleanSupplier`

Used `java.util.Optional` instead of `Maybe`.

Uniformed named with standard library:
* `Maybe.fmap` -> `Maybe.map`
* `Maybe.value` -> `Maybe.get`
* `Maybe.hasValue` -> `Maybe.isPresent`
* `Box.fmap` -> `Box.map`
* `Box.hasContent` -> `Box.isPresent`
* `Either.fmap` -> `Either.map`
* `Either.maybe` -> `Either.right`
* `Pair.fmap` -> `Pair.map`
* `Triple.fmap` -> `Triple.map`
* `TernaryDelegate` -> `TriFunction`
* `TernaryPredicate` -> `TriPredicate`

Removed useless functors:
* `Identity` -> use `Function.identity()` or `x -> x`
* `DropMaybe` -> use `Maybes::drop`
* `EitherToMaybe` -> use `Eithers::right`
* `PureMaybe` -> use `Optional::of`
* `LiftMaybe` -> use `Optional::ofNullable`
* `PureEither` -> use `Either::right`
* `PureBox` -> use `Box::of`
* `MaybeRight` -> use `Eithers::right`
* `MaybeOrElse` -> use lambda
* `MaybeLeft` -> use `Either::left`
* `MaybeIteratorTransformer` -> use `MaybeIterator::new`
* `IsNothing` -> use `Maybes::isEmpty`
* `IsJust` -> use `Optional::isPresent`
* `FromJust` -> use `Optional::get`
* `FmapMaybe` -> invoke `Maybes.lift`
* `FMapBox` -> invoke `Boxes.lift`
* `FlipEither` -> use `Either::flip`
* `FlipPair` -> use `Pair::flip`
* `FlipTriple` -> use `Triple::flip`
* `PairFirst` -> use `Pair::first`
* `PairSecond` -> use `Pair::second`
* `TripleFirst` -> use `Triple::first`
* `TripleSecond` -> use `Triple::second`
* `TripleThird` -> use `Triple::third`
* `TripleRotateLeft` -> use `Triple::rotateLeft`
* `TripleRotateRight` -> use `Triple::rotateRight`
* `Max` -> use `BinaryOperator.maxBy`
* `Min` -> use `BinaryOperator.minBy`
* `Negator` -> invoke `predicate.negate()`
* `Equals` -> invoke `Predicate.isEqual()`
* `NotEquals` -> invoke `Predicate.isEqual().negate()`
* `HasNext` -> use `Iterator::hasNext`
* `IteratorPlucker` -> use `Iterable::iterator`
* `ClassPlucker` -> use `Object::getClass`