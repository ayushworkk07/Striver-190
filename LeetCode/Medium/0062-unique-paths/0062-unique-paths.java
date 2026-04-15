class Solution {
    /*  we try each direction from every path and return the count of both 
        every node returns the value of total count from that to end
    */

    public int uniquePaths(int m, int n) {

        // return f(0,0 , new Integer[m+1][n+1]);

        return tabulization( m , n);

    }

    public int f(int row ,int col , Integer[][]dp){
        if(row >= dp.length-1 || col >= dp[0].length-1)
            return 0;

        if(row == dp.length-2 && col == dp[0].length-2)
            return 1;

        if(dp[row][col] != null)
            return dp[row][col];


        int down = f(row+1,col,dp);
        int right = f(row,col+1,dp);

        return dp[row][col] = down + right;
    }

    public int tabulization(int m , int n){
        int [][] dp = new int [m+1][n+1];

        dp[m-1][n-1] = 1;

        for(int i = m-1 ; i>= 0 ; i--){
            for(int j = n-1 ; j>=0 ; j--){
                
                if (i == m - 1 && j == n - 1) continue;

                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
    }
}