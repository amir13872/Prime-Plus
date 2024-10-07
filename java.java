import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumberCalculator {

    /**
     * Returns a list of prime numbers up to n using the Sieve of Eratosthenes algorithm.
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        if (n < 2) {
            return new ArrayList<>();
        }
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = isPrime[1] = false;  // 0 and 1 are not primes
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    isPrime[multiple] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p <= n; p++) {
            if (isPrime[p]) {
                primes.add(p);
            }
        }
        return primes;
    }

    /**
     * Finds all prime numbers in the range [start, end].
     */
    public static List<Integer> findPrimesInRange(int start, int end) {
        if (start > end || start < 0 || end < 0) {
            return new ArrayList<>();
        }
        List<Integer> primesUpToEnd = sieveOfEratosthenes(end);
        List<Integer> primesInRange = new ArrayList<>();
        for (int p : primesUpToEnd) {
            if (p >= start) {
                primesInRange.add(p);
            }
        }
        return primesInRange;
    }

    /**
     * Provides the last three prime numbers and the count of prime numbers in the range [start, end].
     */
    public static Pair primeStatistics(int start, int end) {
        List<Integer> primesInRange = findPrimesInRange(start, end);
        int countOfPrimes = primesInRange.size();
        List<Integer> lastThreePrimes = new ArrayList<>();
        if (primesInRange.size() >= 3) {
            lastThreePrimes = primesInRange.subList(primesInRange.size() - 3, primesInRange.size());
        } else {
            lastThreePrimes = primesInRange;
        }
        return new Pair(lastThreePrimes, countOfPrimes);
    }

    /**
     * Prompts the user to enter the start and end of the range, with validation.
     */
    public static Pair getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int start, end;
        while (true) {
            try {
                System.out.print("Please enter the start of the range (non-negative integer): ");
                start = scanner.nextInt();
                System.out.print("Please enter the end of the range (non-negative integer): ");
                end = scanner.nextInt();
                if (start > end) {
                    System.out.println("Error: Start of the range must be less than or equal to the end of the range.");
                } else if (start < 0 || end < 0) {
                    System.out.println("Error: Range values must be non-negative.");
                } else {
                    return new Pair(start, end);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter integer values.");
                scanner.next();
            }
        }
    }

    /**
     * Displays the results of the prime number calculations.
     */
    public static void displayResults(int start, int end, List<Integer> lastThreePrimes, int countOfPrimes) {
        if (!lastThreePrimes.isEmpty()) {
            System.out.print("The last prime numbers in the range [" + start + ", " + end + "] are: ");
            for (int i = 0; i < lastThreePrimes.size(); i++) {
                System.out.print(lastThreePrimes.get(i));
                if (i < lastThreePrimes.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("No prime numbers found in the range [" + start + ", " + end + "]");
        }
        System.out.println("The number of prime numbers in this range is: " + countOfPrimes);
    }

    public static void main(String[] args) {
        // Get user input for the range
        Pair userInput = getUserInput();
        int start = userInput.getFirst();
        int end = userInput.getSecond();
        // Calculate and display the results
        Pair results = primeStatistics(start, end);
        List<Integer> lastThreePrimes = results.getFirst();
        int countOfPrimes = results.getSecond();
       
