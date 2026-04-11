class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy , l1 = list1 , l2 = list2;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                curr.next = l1;
                curr = l1;
                l1 = l1.next;
            }
            else{
                curr.next = l2;
                curr = l2;
                l2 = l2.next;
            }
        }

        while(l1!=null){
            curr.next = l1;
            curr = l1;
            l1 = l1.next;
        }

        while(l2!=null){
            curr.next = l2;
            curr = l2;
            l2 = l2.next;
        }

        return dummy.next;
    }
}