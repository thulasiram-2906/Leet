class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) return 0;  
        long count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            count += (n / divider) * i;
            long remainder = n % divider;
            if (remainder >= 2 * i) {
                count += i;
            } else if (remainder >= i) {
                count += remainder - i + 1;
            }
        }
        return (int) count;
    }
}
