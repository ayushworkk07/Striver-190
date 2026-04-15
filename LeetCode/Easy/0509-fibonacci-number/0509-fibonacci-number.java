class Solution {

    public int fib(int n) {
        // return f(n , new Integer[n+1]);      //memoization
        return tabulization(n);
    }

    public int f(int n, Integer[] dp){
        if(n==1) return 1;
        if(n<=0) return 0;

        if(dp[n] != null)
        return dp[n];
    
        return dp[n] = f(n-1,dp)+f(n-2,dp);
    }

    public int tabulization(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        Integer[] dp = new Integer[n+1];

        dp[0] = 0 ; dp[1] = 1;

        for(int i = 2 ; i <= n ; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }
}