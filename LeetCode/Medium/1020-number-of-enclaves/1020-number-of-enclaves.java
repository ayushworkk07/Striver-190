class Solution {
    /* call DFS from boundary nodes to traverse those components and make all them 0
        traverse whole array and return number of land nodes.
    */
    int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int numEnclaves(int[][] board) {
        int m = board.length , n = board[0].length;

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j <n ; j++){
                if((i == 0 || i == m-1 || j == 0 || j == n-1) && board[i][j] == 1){
                    dfs(board,i,j);
                }
            }
        }

        int count = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j <n ; j++){
                if(board[i][j] == 1){
                    count++;
                }
            }
        }

        return count;

    }

    public void dfs(int[][]board,int r ,int c){

        board[r][c] = 0;
        for(int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];

                if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 1) {
                    dfs(board,nr,nc);
                }
        }
    }
}