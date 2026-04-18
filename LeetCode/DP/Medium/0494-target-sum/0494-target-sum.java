class Solution {
    /* we use pick not pick strategy to find all subsequences and check at the end if we have 
        got a valid one
        we use a offset value to add to avoid having negative target as indexes in dp array
    */
    int mod = (int) 1e9 + 7;
    int offset = 1000;
    public int findTargetSumWays(int[] nums, int target) {
        // return f(nums,target,0,0,new Integer[nums.length+1][2*1000 +1]);

        return tabulization(nums,target);
    }

    public int f(int[] coins , int target , int index ,int amount, Integer[][] dp){
        if(index == coins.length){
            if(amount == target)
                return 1;

            return 0;
        }

        if(dp[index][amount+offset]!=null)
            return dp[index][amount + offset];


        int add = f(coins , target,index+1,amount + coins[index],dp);

        int subtract = f(coins,target,index+1,amount - coins[index],dp);

        return dp[index][amount + offset]= (add+subtract)%mod;
    }

    public int tabulization(int[] nums, int target){
        int n = nums.length;
        int[][] dp = new int[n+1][2*1000+1];

        for(int index = n ; index >= 0; index--){
            for(int amount = -1000 ; amount <= 1000 ; amount ++){
                if(index == nums.length){
                    if(amount == target)
                    dp[index][amount+offset] = 1;
                    
                    else
                    dp[index][amount+offset] = 0;

                    continue;
                }

                int add = 0;
                
                if(amount + nums[index] <= 1000)
                add = dp[index+1][amount + nums[index] + offset];


                int subtract = 0;
                
                if(amount - nums[index] >= -1000)
                subtract= dp[index+1][amount - nums[index] + offset];

                dp[index][amount + offset]= (add+subtract)%mod;
            }
        }

        return dp[0][0 + offset];
    }
}