import java.util.*;

class Solution {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int val : row) start.append(val);
        }
        int[][] neighbors = {
            {1, 3},       // 0 can move to 1 or 3
            {0, 2, 4},    // 1 can move to 0, 2, or 4
            {1, 5},       // 2 can move to 1 or 5
            {0, 4},       // 3 can move to 0 or 4
            {1, 3, 5},    // 4 can move to 1, 3, or 5
            {2, 4}        // 5 can move to 2 or 4
        };
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start.toString());
        visited.add(start.toString());
        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) return moves;
                int zeroIdx = curr.indexOf('0');
                for (int nextIdx : neighbors[zeroIdx]) {
                    String nextState = swap(curr, zeroIdx, nextIdx);
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
