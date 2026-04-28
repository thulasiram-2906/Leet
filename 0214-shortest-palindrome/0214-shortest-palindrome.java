class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        int[] pi = new int[combined.length()];
        for (int i = 1; i < combined.length(); i++) {
            int j = pi[i - 1];
            while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
                j = pi[j - 1];
            }
            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        int longestPalindromePrefixLen = pi[combined.length() - 1];
        String prefixToAdd = rev.substring(0, s.length() - longestPalindromePrefixLen);     
        return prefixToAdd + s;
    }
}
