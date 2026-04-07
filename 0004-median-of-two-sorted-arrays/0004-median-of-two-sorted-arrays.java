class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int p1 = 0, p2 = 0;
        int prev = 0, current = 0;
        for (int i = 0; i <= total / 2; i++) {
            prev = current;
            if (p1 < m && (p2 >= n || nums1[p1] < nums2[p2])) {
                current = nums1[p1++];
            } else {
                current = nums2[p2++];
            }
        }
        if (total % 2 != 0) {
            return (double) current;
        }
        return (prev + current) / 2.0;
    }
}
