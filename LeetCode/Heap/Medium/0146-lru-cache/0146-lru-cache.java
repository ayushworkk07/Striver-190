class LRUCache {
    //make node class
    public class Node{
        int key , value;
        Node next , prev;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head = new Node(0,0);
    Node tail = new Node(0,0);

    HashMap<Integer,Node> map;
    int capacity;

    //initialize
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;
    }
    
    //if already exists move to front
    public int get(int key) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            remove(curr);
            insert(curr.key,curr.value);
            return curr.value;
        }

        return -1;
    }
    
    //if already exists move to front , if max size reached remove tail.prev
    public void put(int key, int value) {
        if(map.containsKey(key))
        remove(map.get(key));

        if(map.size() == capacity)
        remove(tail.prev);

        insert(key,value);
    }

    //insert at front
    public void insert(int key , int value){

        Node curr = new Node(key,value);

        map.put(key,curr);

        curr.next = head.next;
        head.next = curr;
        curr.next.prev = curr;
        curr.prev = head;

    }

    public void remove(Node node){

        node.prev.next = node.next;
        node.next.prev = node.prev;

        map.remove(node.key);

    }
}