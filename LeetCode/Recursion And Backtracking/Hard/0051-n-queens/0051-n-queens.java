
class Solution {
    boolean rowQueens[];
    boolean upperLeft[];
    boolean lowerLeft[] ;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        
        rowQueens = new boolean[n];
        upperLeft = new boolean[2*n];
        lowerLeft = new boolean[2*n];

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');

        nQueens(n , 0 ,board, ans );

        return ans;
    }

    public void nQueens(int n , int col , char[][] board , List<List<String>> ans){
        if(col == n){
            buildBoard(board,ans);
            return;
        }

        for(int i = 0;i < n ; i++){
            if(isSafe(i,col,n)){

                board[i][col] = 'Q';
                markQueens(i,col,true,n);

                nQueens(n , col+1,board,ans);

                board[i][col]='.';
                markQueens(i,col,false,n);
            }
        }
    }
    public boolean isSafe(int row , int col , int n){
        if(rowQueens[row] || upperLeft[row+col] || lowerLeft[row-col +(n-1)]) return false;

        return true;
    }
    public void markQueens(int row , int col , boolean flag,int n){
        rowQueens[row] = flag;
        upperLeft[row+col] = flag;
        lowerLeft[row - col +(n-1)] = flag;
    }

    public void buildBoard(char[][] board , List<List<String>> ans){
        List<String> res = new ArrayList<>();
        
        for(char[] row : board)
        res.add(new String(row));

        ans.add(res);
    }
}