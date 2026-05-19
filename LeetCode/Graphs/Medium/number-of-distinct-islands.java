class Solution {
    /* from each cell we call the DFS in same manner and add the string of path in Hashset
    and return set.size()
    */
   int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    int countDistinctIslands(int[][] grid) {
        int m = grid.length , n = grid[0].length;

        HashSet<String> set = new HashSet<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j <n ; j++){
                if(grid[i][j] == 1){
                    StringBuilder ans = new StringBuilder();
                    dfs(grid,i,j,ans);
                    set.add(ans.toString());
                }
            }
        }

        return set.size();
        
    }

    public void dfs(int[][]grid,int r ,int c, StringBuilder path){

        grid[r][c] = -1;        //marking
        for(int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];

                if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 1) {
                    char direction = getDirection(d[0],d[1]);
                    path.append(direction);
                    dfs(grid,nr,nc, path);
                }
        }
        path.append('B');       //backtracking
    }

    public char getDirection(int row , int col){
        if(row == 0 && col == 1)
            return 'R';

        if(row == 0 && col == -1)
            return 'L';

        if(row == 1 && col == 0)
            return 'D';

        return 'U';
    }
}