## Ariel University, School of Computer Science, 2026 Introduction to Computer Science Tutored by @boaz.benmoshe

# Java Assignment Ex.0 - Goldbach Conjecture Variant

## About the Project
This is my solution for Assignment 0 (Ex0).
The goal of this project is to solve a fundamental computer science problem based on a variant of the **Goldbach Conjecture**.
The main task is to design efficient algorithms to find specific pairs of prime numbers with a fixed gap between them.

## How it works
The program works with natural even numbers. Given an even number `n`, the algorithms search for prime numbers (`p`) that satisfy specific conditions related to that gap `n`.
To ensure high performance, the algorithms minimize iterations by skipping even numbers and limiting search ranges using square roots where possible.

## Key Functions implemented in Ex0.java

### 1. `isPrime(long n)`
Determines if a number is prime.
* **Optimization:** Instead of checking all numbers, it checks divisibility by small primes (2, 3, 5, 7) and then iterates only up to `Math.sqrt(n)`.
* **Efficiency:** The loop steps by 6 to check potential primes efficiently.

### 2. `getPrimePair(long start, long n)`
Finds the first prime number `p1` (where `p1 >= start`) such that `p1 + n` is also a prime number.
* The function iterates forward from `start` until a valid pair is found.
* Returns `-1` if the input is invalid.

### 3. `getClosestPrimePair(long start, long n)`
Finds a "clean" prime pair. This means it finds a prime `p3` such that:
1.  `p3` is prime.
2.  `p3 + n` is prime.
3.  **Crucially:** There are **no other prime numbers** in the range between `p3` and `p3 + n` (except for the case where `n=2`).

* **Logic:** The function verifies the gap by running an internal loop between the two numbers to ensure no primes exist there.

### 4. `getMthClosestPrimePair(int m, long n)`
Finds the `m`'th occurrence of a "Closest Prime Pair".
* The function counts how many valid "Closest Prime Pairs" exist starting from 2.
* It returns the `m`'th pair found.
* Returns `2` if no such pair is found (error code).

## Requirements & Testing
* The solution is designed to be efficient using `long` types.
* The code handles edge cases (like `n < 2` or odd numbers).
* No external libraries were used for prime calculations; all logic is implemented from scratch.
