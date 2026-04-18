class Solution {
    /* each node (that is a combination of row ,col1 , col2) give the maxPath from itself to 
    last Node , and also if both robots are on the same node , only 1 picks up value
    */
    int m , n;
    public int cherryPickup(int[][] grid) {
        m = grid.length ; n = grid[0].length;

        // return f(grid , 0 , 0 , n-1 , new Integer[m+1][n+1][n+1]);
        return tabulization(grid);
    }

    public int f(int[][]grid , int row , int col1 , int col2 , Integer[][][] dp){

        if(row >= m || col1 < 0 || col2 < 0 || col1 >=n || col2 >=n)
            return 0;

        if(row == m-1){
            if(col1 != col2)
            return grid[row][col1] + grid[row][col2];

            else
            return grid[row][col1];
        }  

        if(dp[row][col1][col2] != null)
            return dp[row][col1][col2];

        int value = 0;

        if(col1!=col2)
        value = grid[row][col1] + grid[row][col2];

        else
        value = grid[row][col1] + 0;

        int minSum = 0;
        //cover all combination of directions for both
        for(int i = -1 ; i <= 1; i++){
           for(int j = -1 ; j <= 1 ; j++){
                minSum = Math.max(
                    minSum,
                    f(grid,row+1,col1+i,col2+j,dp)
                );
           }
        }

        return dp[row][col1][col2] = value + minSum;
    }

    public int tabulization(int[][] grid){
        int[][][] dp = new int[m+1][n+1][n+1];

        for(int row = m-1 ; row>=0 ; row--){
            for(int col1 = 0 ; col1 < n ;col1++){
                for(int col2 = 0 ; col2 < n ; col2++){

                    if(row == m-1){
                        if(col1 != col2)
                        dp[row][col1][col2] =  grid[row][col1] + grid[row][col2];

                        else
                        dp[row][col1][col2] = grid[row][col1];

                        continue;
                    }  

                    int value = 0;

                    if(col1!=col2)
                    value = grid[row][col1] + grid[row][col2];

                    else
                    value = grid[row][col1] + 0;

                    int minSum = 0;
                    //cover all combination of directions for both
                    for(int i = -1 ; i <= 1; i++){
                        for(int j = -1 ; j <= 1 ; j++){
                            int restPath = checkBoundary(row+1,col1+i,col2+j) ? dp[row+1][col1+i][col2+j] : 0;

                                minSum = Math.max(
                                    minSum,
                                    restPath
                                );
                        }
                    }

                    dp[row][col1][col2] = value + minSum;
                }
            }
        }

        return dp[0][0][n-1];
    }

    public boolean checkBoundary(int row , int col1 , int col2 ){
        if(row >= m || col1 < 0 || col2 < 0 || col1 >=n || col2 >=n)
        return false;
        
        return true;
    }
}