class Solution {
    public int largestRectangleArea(int[] heights) {
        return monotonicStack(heights);
        
        // return nsePSE(heights);
    }

    public int monotonicStack(int[] heights){
       Stack<Integer> st = new Stack<>();
    int maxA = 0;
    int n = heights.length;

    for (int i = 0; i <= n; i++) {
        // Use 0 as a dummy height at the end (i == n) to clear the stack
        int currentHeight = (i == n) ? 0 : heights[i];

        while (!st.isEmpty() && currentHeight < heights[st.peek()]) {
            int h = heights[st.pop()]; // Height of the bar being processed
            
            // If stack is empty, width is 'i'. Else, it's (current index - new top - 1)
            int width = st.isEmpty() ? i : i - st.peek() - 1;
            
            maxA = Math.max(maxA, h * width);
        }
        st.push(i);
    }
    return maxA;
    }

    // 2Pass
    public int nsePSE(int[] heights){

        int nse[] = getSE(heights,true);
        int pse[] = getSE(heights,false);

        int max = Integer.MIN_VALUE;
        for(int i = 0 ;i < heights.length ; i++){
            int idx = Math.max(nse[i],pse[i]);
            if(idx < 0 ) continue;

            //its (n-1)-(p+1)+1 = n-p-1
            //nse[i] which is the next smaller index , earlier to that all are >= h[i]
            int width = nse[i] - pse[i] - 1;

            int area = heights[i] * width;

            max = Math.max(max ,area);
        }
        return max;
    }

    //get NSE or PSE
    public int[] getSE(int heights[] , boolean nse){
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[heights.length];

        if(nse){
            for(int i = heights.length -1 ; i>=0 ; i--){
                int num = heights[i];
                
                while(stack.size() > 0 && heights[stack.peek()] > num){
                    stack.pop();
                }

                if(stack.isEmpty())
                ans[i] = heights.length;

                else
                ans[i] = stack.peek();
                
                stack.push(i);
            }
        }
        else{
            for(int i = 0 ; i< heights.length ; i++){
                int num = heights[i];
                
                while(stack.size() > 0 && heights[stack.peek()] > num){
                    stack.pop();
                }

                if(stack.isEmpty())
                ans[i] = -1;

                else
                ans[i] = stack.peek();
                
                stack.push(i);
            }
        }

        return ans;
    }
}