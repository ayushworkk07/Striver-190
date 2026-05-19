class Solution {
    /* call DFS from boundary nodes to traverse those components and make them '#'
        traverse the all whole array to make everything except boundary components 'X'
    */
    int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public void solve(char[][] board) {
        int m = board.length , n = board[0].length;

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j <n ; j++){
                if((i == 0 || i == m-1 || j == 0 || j == n-1) && board[i][j] =='O'){
                    dfs(board,i,j);
                }
            }
        }

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j <n ; j++){
                if(board[i][j] =='#'){
                    board[i][j] = 'O';
                }
                else
                    board[i][j] = 'X';
            }
        }

    }

    public void dfs(char[][]board,int r ,int c){

        board[r][c] = '#';
        for(int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];

                // Check boundaries AND if it's a fresh orange
                if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'O') {
                    dfs(board,nr,nc);
                }
        }
    }
}