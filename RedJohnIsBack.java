import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;


/**
 * 
 */
public class RedJohnIsBack {


    // **** global variables ****
    static long memo[];
    static HashSet<Integer> hs;


    /**
     * Populate hash map with prime numbers.
     * Auxiliary function.
     */
    static void setPrimes() {

        // **** ****
        for (int i = 2; i <= 217286; i++) {
            if (isPrime(i))
                hs.add(i);
        }
    }


    /**
     * Determine if this number is a prime.
     * Auxiliary function.
     */
    static boolean isPrime (int num) {

        // **** ****
        for (int i = 2; (i * i) <= num; i++){

            // **** check if not a prime ****
            if (num % i == 0)
                return false;
        }

        // **** num is a prime ****
        return true;
    }


    /**
     * Compute combinations of times.
     * Recursive call.
     * Uses memoization.
     */
    static long f(int n) {

        // **** check if in memo ****
        if (memo[n] != -1) return memo[n];

        // **** initialization ****
        long ans = 1;

        // **** recursive call (if needed) ****
        if (n > 3)
            ans = f(n - 1) + f(n - 4);

        // **** memoize answer ****
        memo[n] = ans;

        // ???? ????
        // System.out.println("f <<< n: " + n + " ans: " + ans);

        // **** return answer ****
        return ans;
    }


    /**
     * There is a wall of size 4 x n in the victim's house.
     * The victim has an infinite supply of bricks of size 4 x 1 and 1 x 4 in her house.
     * There is a hidden safe which can only be opened by a particular configuration of bricks.
     * 
     * First we must calculate the total number of ways in which the bricks can be arranged 
     * so that the entire wall is covered.
     * 
     * There is one more step to the puzzle.
     * Call the number of possible arrangements M.
     * Patrick must calculate the number of prime numbers P in the inclusive range 0 - M.
     * 
     * The time complexity of the first part of the problem is O(n) 
     * since we can calculate M using Dynamic Programming.
     * 
     * The time complexity of the second part is O(n log log n).
     * 
     * Hence, the overall time complexity for the problem becomes O(n log log n).
     */
    static int redJohn(int n) {

        // **** set global variables ****
        hs      = new HashSet<Integer>();
        memo    = new long[41];
        Arrays.fill(memo, -1);

        // **** initialization ****
        int ans = 0;
        long m  = 0;


        // **** fill the wall (number of ways) (first problem) ****
        // m = f(n);                    // uses memoization
        m = findTotalWays(n);           // not using memoization

        // ???? ????
        System.out.println("redJohn <<< m: " + m);
        // System.out.println("redJohn <<< memo: " + Arrays.toString(memo));
        // System.out.println("redJohn <<< findTotalWays(" + n + "): " + findTotalWays(n));


        // **** populate hash set with primes (second problem) ****
        setPrimes();

        // **** calculate the number of prime numbers in the inclusive range 0 - m ****
        for (int i = 2; i <= m; i++) {

            // **** count this prime (if needed) ****
            if (hs.contains(i))
                ans++;
        }

        // **** return number of prime numbers in range 0 - m ****
        return ans;
    }


    /**
     * Given a n x 4 matrix where n is a positive number,
     * find the number of ways to fill the matrix with 1 x 4 tiles.
     * 
     * Recursive call.
     * 
     * Find the number of ways to fill an `N × 4` matrix with `1 × 4` tiles
     * https://www.techiedelight.com/find-ways-fill-n-x-4-matrix-with-1-x-4-tiles/
     */
    public static int findTotalWays(int n) {

        // **** base case(s) ****
        if (n < 1) return 0;
        if (n < 4) return 1;
        if (n == 4) return 2;
 
        // **** combine the results of placing 1 tile horizontally 
        //      and placing 4 tiles vertically ****
        return findTotalWays(n - 1) + findTotalWays(n - 4);
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read number of test cases `t` ****
        int t = Integer.parseInt(br.readLine().trim());

        // ???? ????
        System.out.println("main <<< t: " + t);

        // **** loop once per test case ****
        for (int i = 0; i < t; i++) {

            // **** read `n` for the wall size ****
            int n = Integer.parseInt(br.readLine().trim());

            // ???? ????
            System.out.println("main <<< n: " + n);

            // ???? ????
            // System.out.println("main <<< findTotalWays(" + n + "): " + findTotalWays(n));

            // **** call and display result for this test case ****
            System.out.println("main <<< ans: " + redJohn(n));

            // ???? ????
            // System.out.println("main <<< memo: " + Arrays.toString(memo));
        }

        // **** close buffered reader ****
        br.close();
    }
}