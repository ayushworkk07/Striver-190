class Solution {
    /*  we will try all cuts on each node , let the forward nodes give their max
        each node stores the max profit it can give, if it tries all the partitions cuts
    */
    public int cutRod(int[] price) {

        // return f(price , 0 , new Integer[price.length+1]);

        return tabulization(price);
    }

    public int f(int[]price , int index, Integer[] dp ){
        if(index == price.length )
        return 0;
        
        if(dp[index] != null)
            return dp[index];

        int max = 0;

        for(int i = 1 ; i <= price.length;i++){
            
            //1 indexed array
            if(index + i <= price.length){
                int nodeProfit = f(price,index+i,dp) + price[i-1];

                max = Math.max(max,nodeProfit);
            }
        }

        return dp[index] = max;
    }

    public int tabulization(int[]price){
        int n = price.length;

        int dp[]= new int[n+1];

        for(int index = n ; index>=0 ; index--){
            if(index == price.length ){
                dp[index] = 0;
                continue;
            }

            int max = 0;

            for(int i = 1 ; i <= price.length;i++){
            
                //1 indexed array
                if(index + i <= price.length){
                    int nodeProfit = dp[index+i] + price[i-1];

                    max = Math.max(max,nodeProfit);
                }
            }

            dp[index] = max;
        }

        return dp[0];
    }
}