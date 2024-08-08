def sieve_of_eratosthenes(n):
    """
    Returns a list of prime numbers up to n using the Sieve of Eratosthenes algorithm.
    """
    if n < 2:
        return []

    is_prime = [True] * (n + 1)
    is_prime[0] = is_prime[1] = False  # 0 and 1 are not primes

    for p in range(2, int(n ** 0.5) + 1):
        if is_prime[p]:
            for multiple in range(p * p, n + 1, p):
                is_prime[multiple] = False

    return [p for p in range(2, n + 1) if is_prime[p]]


def find_primes_in_range(start, end):
    """
    Finds all prime numbers in the range [start, end].
    """
    if start > end or start < 0 or end < 0:
        return []

    primes_up_to_end = sieve_of_eratosthenes(end)
    return [p for p in primes_up_to_end if p >= start]


def prime_statistics(start, end):
    """
    Provides the last three prime numbers and the count of prime numbers in the range [start, end].
    """
    primes_in_range = find_primes_in_range(start, end)
    count_of_primes = len(primes_in_range)

    last_three_primes = primes_in_range[-3:]
    return last_three_primes, count_of_primes


def get_user_input():
    """
    Prompts the user to enter the start and end of the range, with validation.
    """
    while True:
        try:
            start = int(input("Please enter the start of the range (non-negative integer): "))
            end = int(input("Please enter the end of the range (non-negative integer): "))
            if start > end:
                print("Error: Start of the range must be less than or equal to the end of the range.")
            elif start < 0 or end < 0:
                print("Error: Range values must be non-negative.")
            else:
                return start, end
        except ValueError:
            print("Error: Invalid input. Please enter integer values.")


def display_results(start, end, last_three_primes, count_of_primes):
    """
    Displays the results of the prime number calculations.
    """
    if last_three_primes:
        primes_str = ', '.join(map(str, last_three_primes))
        print(f"The last prime numbers in the range [{start}, {end}] are: {primes_str}")
    else:
        print(f"No prime numbers found in the range [{start}, {end}]")
    print(f"The number of prime numbers in this range is: {count_of_primes}")


def main():
    # Get user input for the range
    start, end = get_user_input()

    # Calculate and display the results
    last_three_primes, count_of_primes = prime_statistics(start, end)
    display_results(start, end, last_three_primes, count_of_primes)


if __name__ == "__main__":
    main()
