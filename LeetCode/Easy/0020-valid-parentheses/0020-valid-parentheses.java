class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i =0 ;i < s.length() ; i++){
            char ch = s.charAt(i);

            if(ch == '(' || ch == '[' || ch == '{' )
            stack.push(ch);

            //mismatch , stack empty
            else{
                if (!check(ch,stack)) return false;
                stack.pop();
            }

        }

        //if stack still has elements
        return stack.isEmpty();
    }

    public boolean check(Character ch , Stack<Character> stack){
        if(stack.isEmpty()) return false;

        char top = stack.peek();

        if(ch == ')' && top == '(') return true;

        else if(ch == ']' && top == '[') return true;

        else if(ch == '}' && top == '{') return true;

        return false;

    }
}