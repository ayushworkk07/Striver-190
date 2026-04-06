class MyQueue {
    Stack<Integer> input ;
    Stack<Integer> output;
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        if(empty()) return -1;
        
        if(output.size()==0){
            pushAll();
        }
        return output.pop();
    }

    public void pushAll(){
        while(input.size()!=0)
            output.push(input.pop());
    }
    
    public int peek() {
        if(empty()) return -1;
        
        if(output.size()==0){
            pushAll();
        }
        return output.peek();
    }
    
    public boolean empty() {
        return output.size()==0 && input.size()==0;
    }
}