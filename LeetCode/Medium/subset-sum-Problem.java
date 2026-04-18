class Solution {

    /*  each node(combination of target and index) stores that if it adds its own contribuition
    or it skips it own contribuiton , can the rest of the array complete the target sum
    */
    static Boolean isSubsetSum(int arr[], int sum) {

        // return f(arr , sum , 0 , new Boolean[arr.length+1][sum+1] );

        return tabulization(arr , sum );
    }

    static boolean f(int arr[], int target , int index , Boolean[][] dp ){
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

    static boolean tabulization(int[]arr , int sum){
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

