import java.util.ArrayList;
import java.util.List;
class Solution {
    private static final String[] MAPPING = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }
    private void backtrack(List<String> result, String digits, StringBuilder current, int index) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        String letters = MAPPING[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);             // 1. Choose
            backtrack(result, digits, current, index + 1); // 2. Explore
            current.deleteCharAt(current.length() - 1);    // 3. Un-choose (backtrack)
        }
    }
}
