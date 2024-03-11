package com.bobocode.fp;

import com.bobocode.fp.exception.InvalidRangeException;

import java.util.stream.IntStream;

/**
 * This class allows calculating the sum of squares of integer numbers in a certain range using a functional approach.
 * It avoids using mutable variables.
 *
 * @author Taras Boychuk
 */
public class SumOfSquares {
    public static void main(String[] args) {
        System.out.println("Sum of squares from 5 to 10 is " + calculateSumOfSquaresInRange(5, 10));
    }

    /**
     * This method calculates the sum of squares of integers in the range.
     *
     * @param startInclusive first element in the range (inclusive)
     * @param endInclusive   last element in the range (inclusive)
     * @return the sum of squares of each element in the range
     */
    static int calculateSumOfSquaresInRange(int startInclusive, int endInclusive) {
        if (endInclusive < startInclusive) {
            throw new InvalidRangeException();
        }

        return IntStream.rangeClosed(startInclusive, endInclusive)
                .map(i -> i * i)
                .sum();
    }
}
