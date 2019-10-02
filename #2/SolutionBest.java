class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r;
        ListNode head = r = new ListNode(0);
        boolean flag = false;
        do {
            int v = 0;
            if (l1 != null) {
                v += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v += l2.val;
                l2 = l2.next;
            }
            if (flag) {
                v++;
                flag = false;
            }
            if (v >= 10) {
                flag = true;
                v -= 10;
            }
            r = r.next = new ListNode(v);
            
        } while (flag || l1 != null || l2 != null);
        
        return head.next;
    }
}