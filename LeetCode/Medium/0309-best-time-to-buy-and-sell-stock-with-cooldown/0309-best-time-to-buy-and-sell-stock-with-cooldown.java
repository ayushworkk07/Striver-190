class Solution {
    /* at each node we try all options 
    1) buy or skip (if bought == 0)
    2) sell or skip (if bought == 1)

    at each sell we index+2 , to take care of cooldown
    every node returns the max profit which can be made from itself to n-1 
    */
    public int maxProfit(int[] prices) {
        // return f(prices , 0 , 0 , new Integer[prices.length+2][2]);

        return tabulization(prices);
    }

    public int f(int[] prices , int index , int bought, Integer[][]dp){
        if(index >= prices.length){
            return dp[index][bought] = 0;
        }

        int profit = 0;
        if(bought == 1){
            int sell = prices[index] + f(prices, index+2,0,dp);    //skipped 1 index cause of cooldown
            int skip = f(prices,index+1,bought,dp);

            profit = Math.max(sell,skip);
        }

        else{

            int buy = -prices[index] + f(prices,index+1,1,dp);
            int skip = f(prices,index+1,0,dp);

            profit = Math.max(buy,skip);
        }

        return dp[index][bought] = profit;
    }

    public int tabulization(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+2][2];

        for(int index = n ; index>=0 ;index--){
            for(int bought = 0 ; bought <= 1 ; bought++){
                if(index >= prices.length){
                     dp[index][bought] = 0;
                     continue;
                }

                int profit = 0;
                if(bought == 1){
                    int sell = prices[index] + dp[index+2][0];    //skipped 1 index cause of cooldown
                    int skip = dp[index+1][bought];

                    profit = Math.max(sell,skip);
                }

                else{

                    int buy = -prices[index] + dp[index+1][1];
                    int skip = dp[index+1][0];

                    profit = Math.max(buy,skip);
                }

                 dp[index][bought] = profit;
            }
        }

        return dp[0][0];
    }
}