class MinStack {
    Stack<Integer> stack , minStack;

    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);

        //push duplicate minimum values to keep the both stacks in sync
        if(minStack.isEmpty() || val <= minStack.peek() )
            minStack.push(val);
    }
    
    public void pop() {
        if(stack.isEmpty()) return;

        //use .eqauls when comparing objects
        if(minStack.peek().equals(stack.pop()))
            minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}