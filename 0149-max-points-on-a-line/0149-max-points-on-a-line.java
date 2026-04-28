import java.util.*;
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int maxPoints = 0;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> slopeCount = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double dy = points[j][1] - points[i][1];
                double dx = points[j][0] - points[i][0];
                double slope = Math.atan2(dy, dx);              
                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
            }          
            for (int count : slopeCount.values()) {
                maxPoints = Math.max(maxPoints, count + 1);
            }
        }      
        return maxPoints == 0 ? 1 : maxPoints;
    }
}
