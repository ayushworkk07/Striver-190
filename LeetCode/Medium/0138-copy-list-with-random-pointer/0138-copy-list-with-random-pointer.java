class Solution {
    public Node copyRandomList(Node head) {
        //add copy nodes between
        Node curr = head;

        while(curr!=null){
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = curr.next.next;
        }
        //traverse and make random of copy nodes point to randoms in copy
        
        curr = head;

        while(curr!=null){
            if(curr.random != null)
            curr.next.random = curr.random.next;
            
            curr = curr.next.next;
        }

        //detach the list
        curr = head;
        Node dummy = new Node(0);
        Node copyTail = dummy;

        curr = head;
        while(curr!=null){
            Node nextOrignal = curr.next.next;
            Node copyNode = curr.next;
            
            copyTail.next = copyNode;
            copyTail = copyNode;

            curr.next = nextOrignal;
            curr = nextOrignal;

        }

        return dummy.next;
    }
}