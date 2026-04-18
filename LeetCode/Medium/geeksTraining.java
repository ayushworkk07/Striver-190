class Solution {
    /*  
    https://www.geeksforgeeks.org/problems/geeks-training/1
        each node gives maximum points from itself to the last node
        - we try all activities from each nodes and also pass a lastVariable which denotes the
        last activity done by the previous day.
    */
    int rows , cols;
    public int maximumPoints(int mat[][]) {
        rows = mat.length ; cols = mat[0].length;
        
        // return f(mat,0,cols,new Integer[rows+1][cols+1] );
        return tabulization(mat);
    }

   public int f(int[][] mat , int index ,int lastActivity, Integer[][] dp ){
        if(index > rows-1)
            return 0;

        if(dp[index][lastActivity]!=null)
            return dp[index][lastActivity];

        int max = Integer.MIN_VALUE ;

        for(int i =0 ; i < cols ; i++){
            if(i == lastActivity) continue;

            int currentActivity = mat[index][i];
            max = Math.max(max ,currentActivity + f(mat,index+1,i,dp) );
        }

        return dp[index][lastActivity] = max;
    }

    public int tabulization(int[][] mat){
        Integer[][] dp = new Integer[rows+1][cols+1];

        dp[rows-1][0] = Math.max(mat[rows-1][1],mat[rows-1][2]);
        dp[rows-1][1] = Math.max(mat[rows-1][0],mat[rows-1][2]);
        dp[rows-1][2] = Math.max(mat[rows-1][1],mat[rows-1][0]);
        dp[rows-1][3] = Math.max(mat[rows-1][0],Math.max(mat[rows-1][1],mat[rows-1][2]));   //max of all 3 for 1st day

        for(int days = rows-2 ; days>= 0 ; days--){
            
            for(int lastActivity = 0 ; lastActivity < cols+1 ;lastActivity++){
                dp[days][lastActivity] = 0;

                for(int i =0 ; i < cols ; i++){
                    if(i == lastActivity) continue;

                    int currentActivity = mat[days][i];
                    dp[days][lastActivity]= Math.max(dp[days][lastActivity] ,currentActivity + dp[days+1][i] );
                }

            }
        }

        return dp[0][cols];
    }
}