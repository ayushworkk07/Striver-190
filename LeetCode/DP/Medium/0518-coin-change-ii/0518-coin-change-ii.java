class Solution {
    /* same question we just have to count no.of ways this time
     */
    public int change(int amount, int[] coins) {
        // return f(coins,amount, 0 , new Integer[coins.length+1][amount+1]);
        return tabulization(coins , amount);
    }


    public int f(int[] coins , int amount , int index , Integer[][] dp){
        if(amount == 0)
            return 1;

        if(index == coins.length){
            if(amount == 0)
                return 1;

            return 0;
        }

        if(dp[index][amount]!=null)
            return dp[index][amount];

        int pick = 0;

        if(coins[index] <= amount)
        pick = f(coins , amount - coins[index],index,dp);

        int notPick = f(coins,amount,index+1,dp);

        return dp[index][amount]= pick  + notPick;
    }

     public int tabulization(int[] coins, int target){
        int n = coins.length;

        int[][] dp = new int[n+1][target+1];

        for(int index = n ; index>= 0 ;index--){
            for(int amount  = 0 ; amount <= target ; amount++){
                if(amount == 0){
                    dp[index][amount]=1;
                    continue;
                }
                

                if(index == coins.length){
                    if(amount == 0)
                        dp[index][amount] = 1;

                    else
                        dp[index][amount]=0;

                    continue;
                }
                

                int pick = 0;

                if(coins[index] <= amount)
                pick = dp[index][amount-coins[index]];

                int notPick = dp[index+1][amount];

                dp[index][amount]= pick + notPick;
            }
        }

        return dp[0][target];
    }
}