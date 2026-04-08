class MyStack {
    Queue<Integer> q ;

    public MyStack() {
        q = new ArrayDeque();
    }
    
    public void push(int x) {
        
        q.offer(x);

        pushAll();
    }
    
    public int pop() {
        if(empty()) return -1;

        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.size() == 0;
    }

    public void pushAll(){
        for(int i =0 ;i < q.size()-1 ; i++)
            q.offer(q.poll());
    }
}