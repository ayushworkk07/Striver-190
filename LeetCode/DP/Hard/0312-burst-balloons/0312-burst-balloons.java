/* f(i,j) returns the max coins which can be collected by popping balloons between i->j
    1)for a range [i,j] we assume the last balloon to burst is arr[k] and get the max answer
    for both partitions [i,k-1] and [k+1,j] and add last balloon's contribution
    
    = arr[i-1]*arr[k]*arr[j+1] + f(i,k-1) + f(k+1,j) -> max of this 
*/
class Solution {
    int n ;
    public int maxCoins(int[] nums) {

        //padding the array - to avoid all the if else cases of out of bounds;
        int arr[] = new int[nums.length+2];
        arr[0] = 1;
        arr[arr.length-1] = 1;
        
        //new length
        this.n = arr.length;

        for(int i = 1; i <= n-2 ;i++)
            arr[i] = nums[i-1];


        // return f(arr,1,n-2,new Integer[n+1][n+1]);
        return tabulization(arr);
    }

    public int f(int[]nums,int start , int end, Integer[][]dp){
        if(start > end)
        return dp[start][end]=0;
        
        if(dp[start][end]!= null)
            return dp[start][end];

        int minCost = Integer.MIN_VALUE;
        //making kth balloon last to burst
        for(int k = start ; k<= end ; k++){
            int current = nums[start-1] * nums[k] * nums[end+1];
            
            int cost = current + f(nums,start,k-1,dp) + f(nums,k+1,end,dp);

            minCost = Math.max(minCost,cost);
        }

        return dp[start][end]= minCost;
    }

    public int tabulization(int[] nums){
        int[][] dp = new int[n+1][n+1];

        for(int start = n-2 ; start >= 1;start--){
            for(int end = start ; end <= n-2 ; end++){
                if(start > end){
                    dp[start][end]=0;
                    continue;
                }

                int minCost = Integer.MIN_VALUE;

                //making kth balloon last to burst
                for(int k = start ; k<= end ; k++){

                    int current = nums[start-1] * nums[k] * nums[end+1];
                    
                    int cost = current + dp[start][k-1] + dp[k+1][end];

                    minCost = Math.max(minCost,cost);
                }

                dp[start][end]= minCost;
            }
        }

        return dp[1][n-2];
    }
}