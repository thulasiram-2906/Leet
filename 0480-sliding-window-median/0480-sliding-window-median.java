import java.util.*;

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        TreeMap<Integer, Integer> small = new TreeMap<>(Collections.reverseOrder());
        TreeMap<Integer, Integer> large = new TreeMap<>();
        int smallSize = 0, largeSize = 0;
        for (int i = 0; i < n; i++) {
            if (smallSize <= largeSize) {
                large.put(nums[i], large.getOrDefault(nums[i], 0) + 1);
                int first = large.firstKey();
                remove(large, first);
                small.put(first, small.getOrDefault(first, 0) + 1);
                smallSize++;
            } else {
                small.put(nums[i], small.getOrDefault(nums[i], 0) + 1);
                int first = small.firstKey();
                remove(small, first);
                large.put(first, large.getOrDefault(first, 0) + 1);
                largeSize++;
            }
            if (i >= k) {
                int out = nums[i - k];
                if (small.containsKey(out)) {
                    remove(small, out);
                    smallSize--;
                } else {
                    remove(large, out);
                    largeSize--;
                }
            }
            if (smallSize < largeSize) {
                int first = large.firstKey();
                remove(large, first);
                small.put(first, small.getOrDefault(first, 0) + 1);
                smallSize++;
                largeSize--;
            } else if (smallSize > largeSize + 1) {
                int first = small.firstKey();
                remove(small, first);
                large.put(first, large.getOrDefault(first, 0) + 1);
                largeSize++;
                smallSize--;
            }
            if (i >= k - 1) {
                if (k % 2 == 1) {
                    result[i - k + 1] = (double) small.firstKey();
                } else {
                    result[i - k + 1] = ((double) small.firstKey() + large.firstKey()) / 2.0;
                }
            }
        } 
        return result;
    }
    private void remove(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1) map.remove(key);
        else map.put(key, count - 1);
    }
}
