class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null||head.next == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head; 

        ListNode curr = dummy;
        int size = 0;

        while(curr.next!=null){
            curr = curr.next;
            size++;
        }

        int i = 1;

        curr = dummy;
        while(i <= size - n){
            curr = curr.next;
            i++;
        }
        if(curr.next == head){
            head = head.next;
        }
        curr.next = curr.next.next;

        return head;
    }
}