class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n <= 1 || k == 0) return 0;
        long[][][] dp = new long[n][k + 1][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = Long.MIN_VALUE / 2;
            }
        }
        dp[0][1][1] = -prices[0]; // Start Long
        dp[0][1][2] = prices[0];  // Start Short
        dp[0][0][0] = 0;          // Do nothing

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = dp[i - 1][j][0];
                if (j > 0) {
                    dp[i][j][0] = Math.max(dp[i][j][0], 
                                  Math.max(dp[i - 1][j][1] + prices[i], 
                                           dp[i - 1][j][2] - prices[i]));
                }
                dp[i][j][1] = dp[i - 1][j][1];
                if (j > 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
                dp[i][j][2] = dp[i - 1][j][2];
                if (j > 0) {
                    dp[i][j][2] = Math.max(dp[i][j][2], dp[i - 1][j - 1][0] + prices[i]);
                }
            }
        }
        long maxProfit = 0;
        for (int j = 0; j <= k; j++) {
            maxProfit = Math.max(maxProfit, dp[n - 1][j][0]);
        }
        return maxProfit;
    }
}
