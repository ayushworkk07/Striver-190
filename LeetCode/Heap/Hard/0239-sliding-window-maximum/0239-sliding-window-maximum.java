class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //monotonic decreasing stack; store indices
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;

        int ans[] = new int[n-k+1];

        for(int i = 0 ;i < n ; i++){
            //if out of window removeFirst 
            if(!dq.isEmpty() && dq.peekFirst() == i-k)
                dq.pollFirst();

            //remove smaller to keep monotonicity
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast();

            //add current
            dq.add(i);

            //store ans for that window
            if(i >= k-1){
                ans[i-k+1] = nums[dq.peekFirst()];
            }
        }

        return ans;
    }
}