class Solution {
    /* f(i) returns the maxSum after partioning for i till n-1 , 
    we try all combinations of partitions for f(i) from to i+k
    add current window contribution  + f(k+1) (calls for further window) 
    */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // return f(arr , k , 0, new Integer[arr.length+1]);
        return tabulization(arr,k);
    }

    public int f(int[] arr , int k , int start , Integer[]dp){
        int n = arr.length;

        if(start == n)
            return dp[start]= 0;

        int maxSum = 0;
        int maxElement = 0;
        //try all combinations
        for(int j = start ; j < Math.min(start+k , n) ;j++){
            maxElement = Math.max(maxElement,arr[j]);
            int len = j-start+1;

            int sum = maxElement * len + f(arr , k , j+1,dp);

            maxSum = Math.max(sum,maxSum);
        }
        
        return dp[start]= maxSum;
    }

    public int tabulization(int[]arr , int k){
        int n = arr.length;
        int[] dp = new int[n+1];


        for(int start = n ; start >=0 ;start--){
            if(start == n){
                dp[start]= 0;

                continue;
            }

            int maxSum = 0;
            int maxElement = 0;
            //try all combinations
            for(int j = start ; j < Math.min(start+k , n) ;j++){
                maxElement = Math.max(maxElement,arr[j]);
                int len = j-start+1;

                int sum = maxElement * len + dp[j+1];

                maxSum = Math.max(sum,maxSum);
            }
            
            dp[start]= maxSum;
        }

        return dp[0];
    }
}