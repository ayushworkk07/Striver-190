/* at each node we have the option to 
    1) buy , skip (if not holding)
    2) sell , skip ( if holding)

    we take max of all combination
*/
class Solution {
    public int maxProfit(int[] prices) {
        // return f(prices , 0 , 0 , new Integer[prices.length+1][2]);

        return tabulization(prices);
    }

    public int f(int[] prices , int index , int bought , Integer[][] dp){
        if(index == prices.length){
            return dp[index][bought] = 0;
        }
        
    
        int profit = 0;

        if(bought == 1){
            
            int sell = prices[index] + f(prices,index+1,0,dp);
            int skip = f(prices , index+1,bought,dp);

            profit = Math.max(sell , skip);
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
        int[][] dp = new int[n+1][2];

        for(int index = n ; index>=0 ; index--){

            for(int bought = 0 ;bought < 2 ; bought++){
                if(index == prices.length){
                    dp[index][bought] = 0;
                    continue;
                }
                
            
                int profit = 0;

                if(bought == 1){
                    
                    int sell = prices[index] + dp[index+1][0];
                    int skip = dp[index+1][bought];

                    profit = Math.max(sell , skip);
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