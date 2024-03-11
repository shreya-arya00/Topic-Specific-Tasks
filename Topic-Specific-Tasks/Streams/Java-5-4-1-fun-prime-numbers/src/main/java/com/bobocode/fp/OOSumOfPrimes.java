import java.util.stream.IntStream;

public class FunctionalSumOfPrimes {
    public static void main(String[] args) {
        int sumOfPrimes = IntStream.iterate(2, n -> n + 1)
                .filter(FunctionalSumOfPrimes::isPrime)
                .limit(20)
                .peek(prime -> System.out.println(prime))
                .sum();

        System.out.println("Sum of first 20 primes: " + sumOfPrimes);
    }

    private static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }
}
