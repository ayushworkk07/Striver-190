class Solution {
    /*  every node return minPath sum from itself
    */
    int m , n;
    public int minPathSum(int[][] grid) {
        m = grid.length ; n = grid[0].length;

        // return f(grid,0,0 , new Integer[m+1][n+1]);

        return tabulization(grid);

    }

    public int f(int grid[][],int row ,int col , Integer[][]dp){
        if(row >= m || col >= n )
            return (int)1e5;

        if(row == m-1 && col == n-1)
            return grid[row][col];

        if(dp[row][col] != null)
            return dp[row][col];


        int down = f(grid,row+1,col,dp);
        int right = f(grid,row,col+1,dp);

        return dp[row][col] = Math.min(down,right) + grid[row][col];
    }

    public int tabulization(int[][] grid){
        int [][] dp = new int [m+1][n+1];


        for(int i = m-1 ; i>= 0 ; i--){
            for(int j = n-1 ; j>=0 ; j--){
                
                //can store the base case inside the tabulization only
                if (i == m - 1 && j == n - 1){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                int down = i+1 >= m ? (int)1e5 : dp[i+1][j];
                int right = j+1 >=n ? (int)1e5 : dp[i][j+1];

                dp[i][j] = Math.min(down ,right) + grid[i][j];
            }
        }

        return dp[0][0];
    }
}