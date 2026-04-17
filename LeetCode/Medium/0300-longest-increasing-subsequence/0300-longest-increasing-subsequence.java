/* at each node we pick or not pick the number and send the lastIndex
    if(currentNumber > lastIndex) we pick it as we need LIS
    else we not pick it 

    each node return LIS from itself till n-1
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        // return f(nums,0,-1 , new Integer[nums.length+1][nums.length+2]);

        return tabulization(nums);
    }

    //as a negative offset of -1 we keep adding lastIndex+1 to convert every index into +ve
    public int f(int[]nums , int index , int lastIndex , Integer[][]dp){
        if(index == nums.length){
            return dp[index][lastIndex+1] = 0;
        }
        
        if(dp[index][lastIndex+1] != null)
        return dp[index][lastIndex+1];

        int pick = 0;

        if(lastIndex == -1 || nums[index] > nums[lastIndex] )
        pick = 1+ f(nums,index+1,index,dp);

        int notPick = f(nums,index+1,lastIndex,dp);

        return dp[index][lastIndex+1]= Math.max(pick,notPick);
    }

    public int tabulization(int[]nums){
        int n = nums.length;

        int[][]dp = new int[n+1][n+2];

        for(int index = n ; index >= 0 ; index--){
            for(int lastIndex = -1 ; lastIndex < n ; lastIndex++){
                if(index == nums.length){
                    dp[index][lastIndex+1] = 0;
                    continue;
                }
                

                int pick = 0;

                // IMPORTANT: when moving to 'index', the new lastIndex is 'index'
                // Mapping 'index' to the DP table requires adding the +1 offset
                if(lastIndex == -1 || nums[index] > nums[lastIndex] )
                pick = 1+ dp[index+1][index+1];

                int notPick = dp[index+1][lastIndex+1];

                dp[index][lastIndex+1]= Math.max(pick,notPick);
            }
        }

        return dp[0][0];
    }
}