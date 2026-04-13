/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;

        //check if k nodes are there to reverse else return head
        for(int i =0  ;i < k ; i++){
            if(curr == null) return head;
            curr = curr.next;
        }
        curr = head;

        //reverse k nodes
        ListNode prev = null , next = null;

        for(int i = 0 ; i < k ; i++){
            next = curr.next ;
            curr.next = prev;
            prev = curr;

            curr = next;
        }

        //head becomes tail now
        if(curr != null)
        head.next = reverseKGroup(curr,k);

        return prev;
    }
}