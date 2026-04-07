//we make monotonic decrease stack to find first left greatest 
// we make a pair class so each entry of stack stores(value , how many days were they lower)
class StockSpanner {
    public class Pair{
        int value ;
        int days;

        Pair(int value , int days){

            this.value = value;
            this.days = days;
        }
    }
    Stack<Pair> stack;
    public StockSpanner() {
        this.stack = new Stack<>();
    }
    
    public int next(int price) {
        //if empty add value , 1 days
        if(stack.isEmpty()){
            stack.push(new Pair(price,1));
            return 1;
        }

        int count = 1;
        //keep popping out smaller and keep adding their days
        while(!stack.isEmpty()&&stack.peek().value <= price){
            count += stack.pop().days;
        }

        //push current 
        stack.push(new Pair(price,count));

        return count;
    }
}