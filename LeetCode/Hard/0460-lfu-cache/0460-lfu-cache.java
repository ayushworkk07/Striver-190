class LFUCache {

    //create node class
    public class Node{
        int key , value , freq;
        Node next , prev;

        Node(int key , int value){
            this.key = key ;
            this.value = value;
            freq = 1;
        }
    }
    
    //create doubly linked list
    public class DLL{

        Node head , tail ;
        int size ;

        DLL(){
            this.head = new Node(0,0);
            this.tail = new Node(0,0);

            this.head.next = tail;
            this.tail.prev = head;
        }

        public void addNode(Node node){

            node.next = head.next;
            head.next = node;
            node.next.prev = node;
            node.prev = head;
            
            size++;
        }

        public void removeNode(Node node){

            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }
    }

    //frequency map , node map and capacity and frequency for LRU 
    HashMap<Integer,DLL> freqMap;
    HashMap<Integer,Node> nodeMap;
    int capacity;
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        freqMap = new HashMap<>();
        nodeMap = new HashMap<>();
        minFreq = 1;
    }
    
    //if key exists return and update 
    public int get(int key) {
        if(nodeMap.containsKey(key)){
            Node node = nodeMap.get(key);

            update(node);

            return node.value;
        }

        return -1;
    }
    
    //if key exists update
    public void put(int key, int value) {
        if(capacity == 0) return ;

        if(nodeMap.containsKey(key)){
            Node node = nodeMap.get(key);
            node.value = value;
            update(node);
        }
        //if key doesnt exist
        else{
            
            //if max capacity removeLRU 
            if(nodeMap.size() == capacity){
                removeLRU();
            }
            
            insertListNode(new Node(key,value));
            minFreq = 1;
        }
    }

    public void removeLRU(){
        DLL list = freqMap.get(minFreq);
        Node LRU = list.tail.prev;

        list.removeNode(LRU);

        nodeMap.remove(LRU.key);

    }

    public void update(Node node){
        removeListNode(node);
        updateFrequency(node);
        insertListNode(node);
    }

    public void removeListNode(Node node){
        DLL list = freqMap.get(node.freq);
        list.removeNode(node);
        
        nodeMap.remove(node.key);
    }

    public void updateFrequency(Node node){
        if(node.freq == minFreq && freqMap.get(minFreq).size == 0)
            minFreq++;

        node.freq++;
    }

    public void insertListNode(Node node){
        freqMap.computeIfAbsent(node.freq,k->new DLL()).addNode(node);
        nodeMap.put(node.key , node);

    }
}