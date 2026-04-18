class Solution {
    /* same concept as 2d array dp
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        // return f(triangle,0,0, new Integer[200][200]);
        return tabulization(triangle);
    }

    public int f(List<List<Integer>> triangle , int row , int col ,Integer[][] dp){
        if(row >= triangle.size())
            return 0;

        if(dp[row][col] != null)
            return dp[row][col];

        int val = triangle.get(row).get(col);
        
        int left = f(triangle,row+1,col,dp);
        int right = f(triangle,row+1,col+1,dp);

        return dp[row][col] = Math.min(left,right) + val;
    }

    public int tabulization(List<List<Integer>> triangle ){
        int[][] dp = new int[200][200];
        int m = triangle.size();

        for(int i = m-1 ;i >=0 ; i--){
            for(int j = i;j>=0 ; j--){

                int val = triangle.get(i).get(j);
        
                int left = dp[i+1][j];
                int right = dp[i+1][j+1];

                dp[i][j] = Math.min(left,right) + val;
            }
        }

        return dp[0][0];
    }
}