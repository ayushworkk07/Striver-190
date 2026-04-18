class Solution {
    /* each f(i,j) returns min mulitplications to multiply i through j
    */
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        
        // return f(arr,1,n-1 , new Integer[n+1][n+1]);
        return tabulization(arr);
    }

    static int f(int[]arr , int start , int end , Integer[][] dp){
        if(start == end)
            return dp[start][end]= 0;

        if(dp[start][end] != null)
            return dp[start][end];

        int min = Integer.MAX_VALUE;
        for(int k = start ; k <= end-1 ; k++){
            int steps = (arr[start-1] * arr[k] * arr[end]) + f(arr , start,k,dp) + f(arr,k+1,end,dp);

            min = Math.min(steps,min);
        }

        return dp[start][end] = min;
    }

    static int tabulization(int[] arr){
        int n = arr.length;

        int[][] dp = new int[n+1][n+1];
        
         //Travel: start moves backwards, end moves forwards
        for(int start = n-1 ; start >= 1 ; start--){
            for(int end = start+1; end<= n-1 ; end++ ){
                if(start == end){
                    dp[start][end]= 0;
                    continue;
                }
                
                // Partition loop: k goes from start to end-1
                int min = Integer.MAX_VALUE;
                for(int k = start ; k <= end-1 ; k++){
                    int steps = (arr[start-1] * arr[k] * arr[end]) + dp[start][k] + dp[k+1][end];

                    min = Math.min(steps,min);
                }

                dp[start][end] = min;
            }
        }
        
        return dp[1][n-1];
    }

}