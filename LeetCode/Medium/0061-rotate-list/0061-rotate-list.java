class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;


        ListNode tail = head;
        int size = 1;
        while(tail.next!=null){
            tail = tail.next;
            size++;
        }

        k = k%size;
        if(k == 0 ) return head;

        ListNode pointer = head;
        int i = 0;
        while(i < size - k-1){
            pointer = pointer.next;
            i++;
        }
            

        tail.next = head;
        head = pointer.next;        //newHead
        pointer.next = null;

        return head;
    }
}