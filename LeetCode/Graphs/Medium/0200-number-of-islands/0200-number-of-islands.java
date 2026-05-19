class Solution {
    /* run DFS from nodes to find connected components , change the visited cells values in the 
    orignal array for space optimization
    */
    public int numIslands(char[][] grid) {
        int m = grid.length , n = grid[0].length;

        int count = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid,i,j);
                }
            }
        }

        return count;
    }

    public void dfs(char[][] grid , int row , int col){
        if(row >= grid.length || col >= grid[0].length || row <0 || col < 0 || grid[row][col] != '1')
        return;

        //marking visited in the array only
        grid[row][col] = '#';
        
        dfs(grid , row+1,col);
        dfs(grid,row,col+1);
        dfs(grid,row-1,col);
        dfs(grid,row,col-1);
    }
}