/* same items can be picked again infinite supply ,  
pick not pick , we call for same index also
*/
class Solution {
    public int knapSack(int val[], int wt[], int W){

        // return f(W , val , wt , 0 , new Integer[val.length+1][W+1]);
        return tabulization(W,val,wt);
    }

    public int f(int w , int[]val , int[]wt , int index , Integer[][] dp){
        if(w == 0 || index == val.length)
        return 0;

        if(dp[index][w]!=null)
            return dp[index][w];

        int pick = 0;
        if(wt[index] <= w)
            pick = f(w-wt[index] ,val,wt , index,dp)+ val[index];

        int notPick = f(w , val , wt , index+1,dp);

        return dp[index][w]= Math.max(pick , notPick);
    }

    public int tabulization(int w, int val[], int wt[]){
        int n = val.length;

        int[][] dp = new int[n+1][w+1];
        
        for(int index = val.length - 1; index >= 0 ; index--){
            for(int weight = 0 ; weight <= w ; weight++){

                int pick = 0;
                if(wt[index] <= weight)
                    pick = dp[index][weight - wt[index]]+ val[index];

                int notPick = dp[index+1][weight];

                dp[index][weight]= Math.max(pick , notPick);
            }
        }

        return dp[0][w];
    }
}