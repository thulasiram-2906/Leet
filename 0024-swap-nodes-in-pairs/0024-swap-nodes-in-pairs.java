class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode point = dummy;
        while (point.next != null && point.next.next != null) {
            ListNode first = point.next;
            ListNode second = point.next.next;
            first.next = second.next;
            second.next = first;
            point.next = second;
            point = first;
        }
        return dummy.next;
    }
}
