package com.bobocode.fp;

import com.bobocode.util.ExerciseNotCompletedException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.function.*;

/**
 * {@link CrazyLambdas} is an exercise class. Each method returns a functional interface and it should be implemented
 * using either lambda or a method reference. Every method that is not implemented yet throws
 * {@link ExerciseNotCompletedException}.
 * <p>
 * TODO: remove exception and implement each method of this class using lambda or method reference
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Taras Boychuk
 */
public class CrazyLambdas {

    public static Supplier<String> helloSupplier() {
        return () -> "Hello";
    }

    public static Predicate<String> isEmptyPredicate() {
        return String::isEmpty;
    }

    public static BiFunction<String, Integer, String> stringMultiplier() {
        return String::repeat;
    }

    public static Function<BigDecimal, String> toDollarStringFunction() {
        return bd -> "$" + bd.toString();
    }

    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        return s -> s.length() >= min && s.length() < max;
    }

    public static IntSupplier randomIntSupplier() {
        return () -> (int) (Math.random() * Integer.MAX_VALUE);
    }

    public static IntUnaryOperator boundedRandomIntSupplier() {
        return bound -> (int) (Math.random() * bound);
    }

    public static IntUnaryOperator intSquareOperation() {
        return x -> x * x;
    }

    public static LongBinaryOperator longSumOperation() {
        return Long::sum;
    }

    public static ToIntFunction<String> stringToIntConverter() {
        return Integer::parseInt;
    }

    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        return () -> x -> n * x;
    }

    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        return func -> func.andThen(String::trim);
    }

    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        return () -> new Thread(runnable);
    }

    public static Consumer<Runnable> newThreadRunnableConsumer() {
        return runnable -> new Thread(runnable).start();
    }

    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
        return runnable -> () -> new Thread(runnable);
    }

    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        return (f, p) -> x -> p.test(x) ? f.applyAsInt(x) : x;
    }

    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        return (functionMap, functionName) -> functionMap.getOrDefault(functionName, x -> x);
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> mapper) {
        return Comparator.comparing(mapper);
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> thenComparing(
            Comparator<? super T> comparator, Function<? super T, ? extends U> mapper) {
        return comparator.thenComparing(mapper);
    }

    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        return () -> () -> () -> "WELL DONE!";
    }
}
