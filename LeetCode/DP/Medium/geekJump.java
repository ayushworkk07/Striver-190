/*      each node will return the minimum energy needed from that to the end
    https://www.geeksforgeeks.org/problems/geek-jump/1
 */
class Solution {
    int minCost(int[] height) {
        int n = height.length;

        // return f(height ,0, new Integer[n+1]);

        return tabulization(height);
    }

    int f(int height[] ,int index, Integer[] dp){

        if(index == height.length -1)
            return 0;
        
        if(dp[index] != null)
            return dp[index];

        int leftMin = f(height,index+1,dp) + Math.abs(height[index]-height[index+1]);

        int rightMin = Integer.MAX_VALUE;
        if(index + 2 < height.length)
        rightMin = f(height,index+2,dp) + Math.abs(height[index]-height[index+2]);


        return dp[index] = Math.min(leftMin,rightMin) ;
    }

    int tabulization(int height[]){
        int n = height.length;

        Integer[] dp = new Integer[n+1];

        dp[n-1] = 0;
        dp[n-2] = 0 + Math.abs(height[n-1]- height[n-2]);

        for(int i = n-3 ; i>=0 ; i--){
            int leftMin = dp[i+1] + Math.abs(height[i] - height[i+1]);

            int rightMin = Integer.MAX_VALUE;

            if(i+2 < n)
            rightMin = dp[i+2] + Math.abs(height[i]- height[i+2]);

            dp[i] = Math.min(leftMin,rightMin);
        }

        return dp[0];
    }
}