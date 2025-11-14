/**
 * This class is a basis for Ex0 (your first assigment),
 * The definition of the Ex0 can be found here: https://docs.google.com/document/d/1UtngN203ttQKf5ackCnXs4UnbAROZWHr/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 * You are asked to complete the functions below amd may add additional functions if needed.

 */
public class Ex0 {
    public final static long ID = 123456789;  //Do update your ID here- I did :)
    /**
     * 1)we start a function to check if a number is a prime number.
     * 2) if n=2 then answer true (2 is prime and pair)
     * 3) if n<2 ans false (we remove 1,0)
     * 4) if n%2 || 3 || 5 || 7 ==0 ans false (we remove all the even num and multipliers of 3,5,7)
     * 5) math.sqrt(n) + 1 -(check for all num until root n)
     * 6) start loop to check if numbers are in the form of 6k+1 || 6k-1 until root n
     * 7) if no divisors were found in the loop then n is prime
     * @param n (Integer) - represented as long
     * @return true if and only if there is no integer (p) within the range of [2,n) which divides n.
     */
    // A
    public static boolean isPrime(long n) {
        if (n == 2|| n == 3|| n == 5|| n == 7) return true; // 2 & 3 & 5 & 7 is prime number
        if (n < 2) return false;  // 0,1 are not prime and can confuse our function
        if (n % 2 == 0 || n % 3 == 0|| n % 5 == 0 || n % 7 == 0) return false; //if it divides by these num It's even or a multiplier so not prime
        // every prime num is always in the form of 6k+1 || 6k-1
        long root = (long) Math.sqrt(n) + 1; // the highest num we need to check it the root (cant divide bigger than root to become n)
        for (long i = 11; i <= root; i += 6) {
            if (n % i == 0) return false;       // checks 6k - 1
            if (n % (i + 2) == 0) return false; // checks 6k + 1
        }
        return true;
    }

    /// ////////////////////
    ///
    /**
     * 1) we start to search the first prime num (p1) > s(positive num), for which p2=p1+n(even & positive) is also a prime number.
     * 2) check if n even and bigger than 2 otherwise in the end return -1
     * 3) start inf loop from s(cuz its smallest)
     * 4) we set p1 to smallest = s in the begging of the loop
     * 5) we define p2= p1 + n to find p2
     * 6) check in isprime if p1 & p2 are prime if both true= return p1
     * 7) else - next num plus 1 = keep searching
     * 8) return ans - if we found a prime pair, ans is p1; otherwise it remains -1
     * @param start - a starting value from which p1 should be searched for.
     * @param n     - a positive (even) integer value.
     * @return the first prime number p1 such that: i) p1>=start, ii) p1+n is a prime number.
     * in case a wrong value is given to the function
     * (n<0 or n is an odd number) the function should return -1.
     */
    // B
    public static long getPrimePair(long start, long n) {
        long ans = -1;
        if (n >= 2 && n % 2 == 0) { // we make sure that n is even
            long p1 = start;// p1=s(start)
            while (ans == -1) { // loop until we find a prime pair
                if (isPrime(p1) && isPrime(p1 + n)) {
                    ans = p1; // we found a pair and we save it
                }
                p1 += 1; //move forwards until we get pair prime
            }
        }
        return ans;
    }

    /**
     *  1) we want to find the smallest prime p3 and that there is no prime number between pair prime so that
     *    p4 = p3 + n is also prime, and there is no prime number between p3 and p4.
     *  2) we start to check if n < 2 or n is odd if true - return -1 (invalid input)
     *  3) start of loop p3 = start (we begin checking from the s/start which is the lowest)
     *  4) we want to check if p3 < 2 if true then set p3 = 2 (there are no primes below 2)
     *  5) if p3 is even and not 2 then p3 = p3 + 1 (skip even numbers)
     *  6) give result = -1 (means "not found yet")
     *  7) we start a loop to keep searching until we find a valid prime pair
     *  8) we check if p3 isprime
     *  9) we set p4 = p3 + n
     *  10) we exclude a specif gap where there is small gap where there cant be a prime between(3,5)
     *  11) else if p4 is prime:
     *  12) we assume no prime exists between p3 and p4
     *  13) we start a loop to certify that there is no prime num between them, and we skip even numbers
     *  14) if there is a prime between then break loop and go to next prime number
     *  15) if there isn't a prime number between return p3
     *  16) after loop ends → return result (the smallest p3 that satisfies all conditions)
     *
     * @param start a positive integer which is the lower bound of p1.
     * @param n     - a positive even integer.
     * @return a prime number p1>=start that the following prime number is p1+n.
     */
    //C
    public static long getClosestPrimePair(long start, long n) {
        /// Add your code below ///
            if (n < 2 || n % 2 != 0) return -1; // checks in n smaller than 2 or even
            long p3 = start; // we start to check from s cuz its the smallest
            if (p3 < 2) p3 = 2;
            if (p3 % 2 == 0 && p3 != 2) p3 +=1; //removes all even num
            long result = -1; //if it is even then give error

            while (result == -1) {
                if (isPrime(p3)) { //check if p3 is prime-if true enter a loop to find p4 and prime between
                    long p4 = p3 + n;  // we set p4
                    if (n == 2) { //if the space between them is 2 its too small to have a prime in(3,5)
                        result = p3; // they are a match cuz there cant be a prime between like
                    } else if (isPrime(p4)) { //we check if p4 prime
                        boolean hasPrimeBetween = false; // we assume there is no prime between
                        long i = p3 + 1; //we start to check if p3 has a prime num between the pair
                        if (i % 2 == 0) i +=1 ; //if i even go to odd num
                        for (; i < p4; i += 2) { //loop for all numbers between only odd
                            if (isPrime(i)) { // checks if the number is prime
                                hasPrimeBetween = true; //we found a prime in between the numbers
                                break;
                            }
                        }
                        if (!hasPrimeBetween) { //if there is no prime in between the pair then we found
                            result = p3;
                        }
                    }
                }
                if (p3 == 2) { //if we checked 2 then go to 3
                    p3 = 3;}
                else { //else continue searching for prime num that is odd
                    p3 += 2;}
            }
            return result;
        }
        /// ////////////////// ///


    /**
     * we want to find the closest prime pair when (p5, p6 = p5 + n)
     * -with no prime between them with the distance between them beeing m,
     * and it is the first pair of that kind and we count how much there are
     * 1) we make sure that the input for n & m is correct
     * 2) start p5 = 2 cuz its the smallest num
     * 3) count = 0 (how many closest pairs found)
     * 4) we start a loop until count <= m:
     * 5) we check only if p5 is prime num otherwise go search next odd num that is prime
     * 6) we set p6 = p5 + n
     * 7) we check if p6 is prime and if there is a prime num between p5 - p6 and the distance between them is >2               for i = p5+1 to p6-1 (only odd i):
     * 8) if there is a prime between them then break the loop and search for next prime pair
     * 9) if there is no prime between than add +1 to count
     * 10) if we did count all the m then return p5
     * 11) if we didnt find a matching pair go to next odd num
     * 12) return 2 (there is no such pair)
     *
     * @param m a none negative integer.
     * @param n - a positive even integer.
     * @return a prime number p1>=start that the following prime number is p1+n.
     *
     */
    // D
    public static long getMthClosestPrimePair(int m, long n) {
        if (m < 0 | n < 0 | n % 2 != 0) {
            System.err.println("Invalid input: got m=" + m + ", n=" + n + "  |  m should be >=0 & n should be a positive even integer ");
            return -1;
        }
        /// Add your code below ///
        if (m < 0 || n < 2 || n % 2 != 0) return -1; //m can't be negative, n odd or too small - error
        long p5 = 2; //start from the smallest prime num
        long count = 0; //number of closest pairs found so far set to count
        while (count <= m) { //keep going until we count m+1 pairs(indexes like arr's that's why +1)
            if (isPrime(p5)) { //only check if p5 is prime
                long p6 = p5 + n; //we set p6 second number in pair
                if (isPrime(p6)) { //we only check if p6 is prime if there is a prime between
                    boolean hasPrimeBetween = false; //assume no prime between
                    if (n != 2) { //only goes in loop if there is a bigger distance than 2 between num
                        long i = p5 + 1; //start from next number cuz then num itself is prime
                        if (i % 2 == 0) i += 1; //skip even num it is even
                        for (; i < p6; i += 2) { //check only odd numbers
                            if (isPrime(i)) { //found a prime in between
                                hasPrimeBetween = true; //there is a prime num between p5-p6 then break loop and increase num
                                break; //no need to check more its not a good prime pair
                            }
                        }
                    }
                    if (!hasPrimeBetween) { //if there is no prime between then it's a closest pair
                        count++; //count this pair
                        if (count == m + 1) { //m starts from 0  first pair count=1
                            return p5; //return smaller prime pair of m distance
                        }
                    }
                }
            }
            if (p5 == 2) { //if were checking for 2
                p5 = 3; //go to 3
            } else {
                p5 += 2;} //skip even numbers
        }
        return 2; //no such pair found (Boaz used 2 as error not found)
        /// ////////////////// ///
    }   /// ////////////////// ///
}

/// //////// Priate Functions - you are welcome to add additional (private) functions below.

