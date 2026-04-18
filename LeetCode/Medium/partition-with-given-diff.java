/* same question as count subset sum just we need to count for
s1-s2 = diff , s1 + s2 = totalSum , so s1 = T-d/2

where T-d should be > 0 and even
*/
class Solution {
    int mod = (int) 1e9+7;
    public int countPartitions(int[] arr, int diff) {
        int total = 0;

        for(int number : arr) total+= number;

        int target = (total - diff)/2;

        if(total-diff < 0) return 0;
        if((total-diff) % 2 == 1) return 0;     //should be even to divide by 2
  
        // return f(arr , target , 0 , new Integer[arr.length+1][target+1] );

        return tabulization(arr , target );   
    }

    public int f(int arr[], int target , int index , Integer[][] dp ){
        if(index == arr.length ){
            if(target == 0)
                return 1;

            return 0;
        }

        if(target < 0)
            return 0;
            
        if(dp[index][target]!=null)
            return dp[index][target];

        int pick = f(arr , target - arr[index] , index+1,dp);
        int notPick = f(arr, target , index+1,dp);

        return dp[index][target]= (pick + notPick)%mod;
    }

    public int tabulization(int[]arr , int sum){
        int[][] dp = new int[arr.length +1 ][sum+1];
        
        dp[arr.length][0]= 1;
        
        for(int index = arr.length-1  ; index>=0 ; index--){
            for(int target = 0 ; target <= sum ; target++){

                    
                int pick = 0;

                if(target - arr[index] >= 0)
                pick = dp[index+1][target-arr[index]];

                int notPick = dp[index+1][target];

                dp[index][target] = (pick + notPick)%mod;
            }
        }

        return dp[0][sum];
    }
}