import java.util.*;
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new int[]{b[0], -b[2]}); 
            points.add(new int[]{b[1], b[2]});
        }
        Collections.sort(points, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        TreeMap<Integer, Integer> heights = new TreeMap<>(Collections.reverseOrder());
        heights.put(0, 1);
        int prevMaxHeight = 0;
        
        for (int[] p : points) {
            int x = p[0];
            int h = p[1];
            
            if (h < 0) { 
                heights.put(-h, heights.getOrDefault(-h, 0) + 1);
            } else { 
                if (heights.get(h) == 1) heights.remove(h);
                else heights.put(h, heights.get(h) - 1);
            }
            
            int currentMaxHeight = heights.firstKey();
            if (currentMaxHeight != prevMaxHeight) {
                result.add(Arrays.asList(x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }      
        return result;
    }
}
