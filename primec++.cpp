#include <iostream>
#include <vector>
#include <cmath>
#include <stdexcept>

/**
 * Returns a vector of prime numbers up to n using the Sieve of Eratosthenes algorithm.
 */
std::vector<int> sieve_of_eratosthenes(int n) {
    if (n < 2) {
        return {};
    }
    std::vector<bool> is_prime(n + 1, true);
    is_prime[0] = is_prime[1] = false;  // 0 and 1 are not primes
    for (int p = 2; p <= std::sqrt(n); ++p) {
        if (is_prime[p]) {
            for (int multiple = p * p; multiple <= n; multiple += p) {
                is_prime[multiple] = false;
            }
        }
    }
    std::vector<int> primes;
    for (int p = 2; p <= n; ++p) {
        if (is_prime[p]) {
            primes.push_back(p);
        }
    }
    return primes;
}

/**
 * Finds all prime numbers in the range [start, end].
 */
std::vector<int> find_primes_in_range(int start, int end) {
    if (start > end || start < 0 || end < 0) {
        return {};
    }
    std::vector<int> primes_up_to_end = sieve_of_eratosthenes(end);
    std::vector<int> primes_in_range;
    for (int p : primes_up_to_end) {
        if (p >= start) {
            primes_in_range.push_back(p);
        }
    }
    return primes_in_range;
}

/**
 * Provides the last three prime numbers and the count of prime numbers in the range [start, end].
 */
std::pair<std::vector<int>, int> prime_statistics(int start, int end) {
    std::vector<int> primes_in_range = find_primes_in_range(start, end);
    int count_of_primes = primes_in_range.size();
    std::vector<int> last_three_primes;
    if (primes_in_range.size() >= 3) {
        last_three_primes = {primes_in_range.end() - 3, primes_in_range.end()};
    } else {
        last_three_primes = primes_in_range;
    }
    return {last_three_primes, count_of_primes};
}

/**
 * Prompts the user to enter the start and end of the range, with validation.
 */
std::pair<int, int> get_user_input() {
    int start, end;
    while (true) {
        try {
            std::cout << "Please enter the start of the range (non-negative integer): ";
            std::cin >> start;
            std::cout << "Please enter the end of the range (non-negative integer): ";
            std::cin >> end;
            if (start > end) {
                std::cout << "Error: Start of the range must be less than or equal to the end of the range.\n";
            } else if (start < 0 || end < 0) {
                std::cout << "Error: Range values must be non-negative.\n";
            } else {
                return {start, end};
            }
        } catch (const std::exception& e) {
            std::cout << "Error: Invalid input. Please enter integer values.\n";
            std::cin.clear();
            std::cin.ignore(10000, '\n');
        }
    }
}

/**
 * Displays the results of the prime number calculations.
 */
void display_results(int start, int end, const std::vector<int>& last_three_primes, int count_of_primes) {
    if (!last_three_primes.empty()) {
        std::cout << "The last prime numbers in the range [" << start << ", " << end << "] are: ";
        for (size_t i = 0; i < last_three_primes.size(); ++i) {
            std::cout << last_three_primes[i];
            if (i < last_three_primes.size() - 1) {
                std::cout << ", ";
            }
        }
        std::cout << "\n";
    } else {
        std::cout << "No prime numbers found in the range [" << start << ", " << end << "]\n";
    }
    std::cout << "The number of prime numbers in this range is: " << count_of_primes << "\n";
}

int main() {
    // Get user input for the range
    auto [start, end] = get_user_input();
    // Calculate and display the results
    auto [last_three_primes, count_of_primes] = prime_statistics(start, end);
    display_results(start, end, last_three_primes, count_of_primes);
    return 0;
}
