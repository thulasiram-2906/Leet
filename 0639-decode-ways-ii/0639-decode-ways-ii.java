class Solution {
    public int numDecodings(String s) {
        long mod = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0') return 0;
        dp[1] = (s.charAt(0) == '*') ? 9 : 1;

        for (int i = 2; i <= n; i++) {
            char curr = s.charAt(i - 1);
            char prev = s.charAt(i - 2);
            if (curr == '*') {
                dp[i] = (dp[i] + 9 * dp[i - 1]) % mod;
            } else if (curr != '0') {
                dp[i] = (dp[i] + dp[i - 1]) % mod;
            }
            if (prev == '*') {
                if (curr == '*') {
                    dp[i] = (dp[i] + 15 * dp[i - 2]) % mod;
                } else if (curr <= '6') {
                    dp[i] = (dp[i] + 2 * dp[i - 2]) % mod;
                } else {
                    dp[i] = (dp[i] + dp[i - 2]) % mod;
                }
            } else if (prev == '1') {
                if (curr == '*') {
                    dp[i] = (dp[i] + 9 * dp[i - 2]) % mod;
                } else {
                    dp[i] = (dp[i] + dp[i - 2]) % mod;
                }
            } else if (prev == '2') {
                if (curr == '*') {
                    dp[i] = (dp[i] + 6 * dp[i - 2]) % mod;
                } else if (curr <= '6') {
                    dp[i] = (dp[i] + dp[i - 2]) % mod;
                }
            }
        }
        return (int) dp[n];
    }
}
