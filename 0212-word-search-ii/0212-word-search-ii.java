import java.util.*;
class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null; 
    }
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode curr = root;
            for (char c : w.toCharArray()) {
                curr.children.putIfAbsent(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.word = w;
        }
        List<String> result = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.children.containsKey(board[i][j])) {
                    dfs(board, i, j, root, result);
                }
            }
        }
        return result;
    }
    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        char letter = board[r][c];
        TrieNode currNode = node.children.get(letter);
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null; 
        }
        board[r][c] = '#';
        int[] rowOffset = {-1, 1, 0, 0};
        int[] colOffset = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int newR = r + rowOffset[i];
            int newC = c + colOffset[i];
            if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length 
                && board[newR][newC] != '#' && currNode.children.containsKey(board[newR][newC])) {
                dfs(board, newR, newC, currNode, result);
            }
        }
        board[r][c] = letter;
        if (currNode.children.isEmpty()) {
            node.children.remove(letter);
        }
    }
}
