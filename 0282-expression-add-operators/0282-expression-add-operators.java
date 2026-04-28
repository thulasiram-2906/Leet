import java.util.*;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) return result;
        backtrack(result, new StringBuilder(), num, target, 0, 0, 0);
        return result;
    }
    private void backtrack(List<String> result, StringBuilder path, String num, int target, int pos, long eval, long prevValue) {
        if (pos == num.length()) {
            if (target == eval) {
                result.add(path.toString());
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            long curr = Long.parseLong(num.substring(pos, i + 1));
            int len = path.length();
            if (pos == 0) {
                backtrack(result, path.append(curr), num, target, i + 1, curr, curr);
                path.setLength(len); 
            } else {
                backtrack(result, path.append("+").append(curr), num, target, i + 1, eval + curr, curr);
                path.setLength(len);
                backtrack(result, path.append("-").append(curr), num, target, i + 1, eval - curr, -curr);
                path.setLength(len);
                backtrack(result, path.append("*").append(curr), num, target, i + 1, eval - prevValue + (prevValue * curr), prevValue * curr);
                path.setLength(len);
            }
        }
    }
}
