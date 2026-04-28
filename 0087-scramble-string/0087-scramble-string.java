import java.util.*;

class Solution {
    // Map to store results of previously calculated subproblems
    Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base cases
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        
        String key = s1 + " " + s2;
        if (memo.containsKey(key)) return memo.get(key);

        // Pruning: check if both strings have the same character frequencies
        if (!hasSameLetters(s1, s2)) {
            memo.put(key, false);
            return false;
        }

        int n = s1.length();
        for (int i = 1; i < n; i++) {
            // Case 1: No swap at this split point
            // s1: [left_i][right_n-i]  s2: [left_i][right_n-i]
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: Swapped at this split point
            // s1: [left_i][right_n-i]  s2: [left_n-i][right_i]
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && 
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    private boolean hasSameLetters(String s1, String s2) {
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
}
