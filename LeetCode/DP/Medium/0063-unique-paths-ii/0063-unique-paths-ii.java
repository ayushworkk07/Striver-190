class Solution {
    /*  we try each direction from every path and return the count of both 
        every node returns the value of total count from that to end
    */

        int m , n;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length ; n = obstacleGrid[0].length;

        // return f(obstacleGrid,0,0 , new Integer[m+1][n+1]);

        return tabulization(obstacleGrid);

    }

    public int f(int obstacleGrid[][],int row ,int col , Integer[][]dp){
        if(row >= m || col >= n || obstacleGrid[row][col] == 1)
            return 0;

        if(row == m-1 && col == n-1)
            return 1;

        if(dp[row][col] != null)
            return dp[row][col];


        int down = f(obstacleGrid,row+1,col,dp);
        int right = f(obstacleGrid,row,col+1,dp);

        return dp[row][col] = down + right;
    }

    public int tabulization(int[][] obstacleGrid){
        int [][] dp = new int [m+1][n+1];


        for(int i = m-1 ; i>= 0 ; i--){
            for(int j = n-1 ; j>=0 ; j--){
                
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                
                //can store the base case inside the tabulization only
                if (i == m - 1 && j == n - 1){
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
    }
}