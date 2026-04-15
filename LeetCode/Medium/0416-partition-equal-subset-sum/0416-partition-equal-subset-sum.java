class Solution {
    public boolean canPartition(int[] arr) {

        int sum = 0;
        for(int number : arr) sum+= number;

        if(sum%2 !=0) return false;     //if sum = odd we cannot divide it in 2 subsets

        //if sum = even , find one subset equals to target(sum/2) if yes then we have 2 subsets

        // return f(arr , sum , 0 , new Boolean[arr.length+1][sum+1] );

        return tabulization(arr , sum/2 );
    }

    public boolean f(int arr[], int target , int index , Boolean[][] dp ){
        if(index >= arr.length || target < 0)
            return false;

        if(target == 0 || target - arr[index] == 0)
        return true;
            
        if(dp[index][target]!=null)
            return dp[index][target];

        boolean pick = f(arr , target - arr[index] , index+1,dp);
        boolean notPick = f(arr, target , index+1,dp);

        return dp[index][target] = pick || notPick;
    }

    public boolean tabulization(int[]arr , int sum){
        boolean[][] dp = new boolean[arr.length +1 ][sum+1];

        for(int index = arr.length - 1 ; index>=0 ; index--){
            for(int target = 0 ; target <= sum ; target++){

                if(target == 0 || target - arr[index] == 0){
                    dp[index][target] = true;
                    continue;
                }
                    
                boolean pick = false;

                if(target - arr[index] >= 0)
                pick = dp[index+1][target-arr[index]];

                boolean notPick = dp[index+1][target];

                dp[index][target] = pick || notPick;
            }
        }

        return dp[0][sum];
    }
}