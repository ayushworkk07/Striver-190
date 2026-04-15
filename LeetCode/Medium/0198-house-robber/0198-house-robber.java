class Solution {
    /*     each node stores maxProfit if it picks itself or doesnt pick itself and calls both function calls
        whichever is the maximum path we store that as 
    */
    public int rob(int[] nums) {
        int n = nums.length;
        //  return f(nums,0 , new Integer[n+1]);      //memoization
        return tabulization(nums);
    }

    public int f(int nums[],int index , Integer[] dp ){
        if(index >= nums.length)
            return 0;

        if(index == nums.length -1)
            return nums[index];
        
        if(dp[index]!=null)
            return dp[index];


        int pick = f(nums,index+2,dp) + nums[index];
        
        int notPick = f(nums,index+1,dp) ;

        return dp[index] = Math.max(pick,notPick);
    }

    public int tabulization(int nums[]){
        int n = nums.length;

        Integer[] dp = new Integer[n+1];
        dp[n-1] = nums[n-1];
        dp[n] = 0;

        for(int i = n-2 ; i>= 0 ; i--){
            int pick = f(nums,i+2,dp) + nums[i];
        
            int notPick = f(nums,i+1,dp) ;

            dp[i] = Math.max(pick,notPick);
        }

        return dp[0];
    }
}