class Solution {
    /* at each node we either 
        1) buy or skip (if no holdings)
        2) sell or skip (holdings)

        and also keep track of the transactions , which only gets ++ at sells only

        at each node we try all combination of buying , selling or skipping and return the max profit
    */
    int maxTransactions ;
    public int maxProfit(int k,int[] prices) {
        maxTransactions = k;
        // return f(prices , 0 , 0 , 0, new Integer[prices.length+1][2][maxTransactions+1]);
        return tabulization(prices);
    }

    public int f(int[]prices , int index ,int bought, int transactions , Integer[][][] dp){

        if(transactions >= maxTransactions || index >= prices.length){
            return dp[index][bought][transactions] = 0;
        }

        if(dp[index][bought][transactions] != null)
        return dp[index][bought][transactions];

        int profit = 0;
        if(bought == 1){
            int sell = prices[index] + f(prices,index+1,0, transactions+1,dp);
            int skip = f(prices,index+1,bought, transactions,dp);

            profit = Math.max(sell ,skip);
        }
        else{
            int buy = -prices[index] + f(prices,index+1,1,transactions,dp);
            int skip = f(prices,index+1,0, transactions,dp);

            profit = Math.max(buy , skip);
        }

        return dp[index][bought][transactions] = profit;
    }

    public int tabulization(int[] prices){
        int n = prices.length;

        int[][][] dp = new int[n+1][2][maxTransactions+1];

        for(int index = n ; index >=0 ; index--){
            for(int bought = 0 ; bought <=1 ;bought++ ){
                for(int transactions = 0 ; transactions <= maxTransactions; transactions++){
                    if(transactions >= maxTransactions || index >= prices.length){
                        dp[index][bought][transactions] = 0;
                        continue;
                    }

                    int profit = 0;
                    if(bought == 1){
                        int sell = prices[index] + dp[index+1][0][transactions+1];
                        int skip = dp[index+1][bought][transactions];

                        profit = Math.max(sell ,skip);
                    }
                    else{
                        int buy = -prices[index] + dp[index+1][1][transactions];
                        int skip = dp[index+1][0][transactions];

                        profit = Math.max(buy , skip);
                    }

                    dp[index][bought][transactions] = profit;

                }
            }
        }

        return dp[0][0][0];
    }
}