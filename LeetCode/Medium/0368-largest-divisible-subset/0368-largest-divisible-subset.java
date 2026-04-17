/* sort the array and find Longest increasing divisible subsequence 
print the subsequence by iterating from 0,0 and going all the way down in the dp table
checking pick condition for each and adding to answer
*/
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        return tabulization(nums);
    }
    public int f(int[]arr , int index , int lastIndex , Integer[][] dp){
        if(index == arr.length){
            return dp[index][lastIndex+1] = 0;
        }

        if(dp[index][lastIndex+1] != null)
            return dp[index][lastIndex];

        int pick = 0;
        if(lastIndex == -1 || (arr[index] % arr[lastIndex] == 0)){
            pick = 1+ f(arr,index+1,index,dp);
        }

        int notPick = f(arr,index+1,lastIndex ,dp);

        return dp[index][lastIndex+1] = Math.max(pick,notPick);
    }

    public ArrayList<Integer> tabulization(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n+1][n+2];

        for(int index = n ; index >= 0; index--){
            for(int lastIndex = -1 ; lastIndex < n ;lastIndex++){
                if(index == arr.length){
                    dp[index][lastIndex+1] = 0;
                    continue;
                }


                int pick = 0;
                if(lastIndex == -1 || (arr[index] % arr[lastIndex] == 0)){
                    pick = 1+ dp[index+1][index+1];
                }

                int notPick = dp[index+1][lastIndex+1];

                dp[index][lastIndex+1] = Math.max(pick,notPick);
            }
        }

        return printLIS(dp , arr );
    }

    public ArrayList<Integer> printLIS(int[][]dp,int[]arr){
        int lastIndex = -1;
        int n = arr.length;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int index = 0 ;index < n ;index++){
            boolean canPick = (lastIndex == -1) || (arr[index] % arr[lastIndex] == 0);

            if(canPick && 1+dp[index+1][index+1] >= dp[index+1][lastIndex+1]){
                ans.add(arr[index]);
                lastIndex = index;
            }
                
        }

        return ans;
    }
}