class Solution {
    static final long MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long evenWays = power(5, evenCount);
        long oddWays = power(4, oddCount);

        return (int)((evenWays * oddWays) % MOD);
    }

    private long power(long x, long y) {
        long result = 1;
        x = x % MOD;

        while (y > 0) {
            if ((y & 1) == 1)
                result = (result * x) % MOD;

            x = (x * x) % MOD;
            y >>= 1;
        }

        return result;
    }
}
