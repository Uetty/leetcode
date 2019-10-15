/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i = 0;
        ListNode p = head;
        while (i++ < n - 1) {
            p = p.next;
        }
        if (p.next == null) {
            return head.next;
        }
        
        p = p.next;
        ListNode rnNode = head;
        while ((p = p.next) != null) {
            rnNode = rnNode.next;
        }
        rnNode.next = rnNode.next.next;
        return head;
    }
}