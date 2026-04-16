class Solution {
    /*  
        pick not pick , as we have infinite supply , we will try for same indexes also
        - trying all subsequence which gets us amount = 0 and taking minimum of coin change of them
    */
    public int coinChange(int[] coins, int amount) {
        if(amount == 0 ) return 0;
        
        int count = 0;
        
        // count = f(coins , amount , 0 , new Integer[coins.length+1][amount+1]);

        count =  tabulization(coins , amount);

        if(count >= (int)1e5)
            return -1;

        return count;
    }

    public int f(int[] coins , int amount , int index , Integer[][] dp){
        if(amount == 0)
            return 0;

        if(index >= coins.length)
            return (int)1e5;

        if(dp[index][amount]!=null)
            return dp[index][amount];

        int pick = (int)1e5;

        if(coins[index] <= amount)
        pick = f(coins , amount - coins[index],index,dp)+1;

        int notPick = f(coins,amount,index+1,dp);

        return dp[index][amount]=Math.min(pick,notPick);
    }

    public int tabulization(int[] coins, int target){
        int n = coins.length;

        int[][] dp = new int[n+1][target+1];

        for(int index = n ; index >= 0 ;index--){
            for(int amount  = 0 ; amount <= target ; amount++){
                if(amount == 0){
                    dp[index][amount]=0;
                    continue;
                }
                

                if(index >= coins.length){
                    dp[index][amount] = (int)1e5;
                    continue;
                }
                

                int pick = (int)1e5;

                if(coins[index] <= amount)
                pick = dp[index][amount-coins[index]]+1;

                int notPick = dp[index+1][amount];

                dp[index][amount]=Math.min(pick,notPick);
            }
        }

        return dp[0][target];
    }
}