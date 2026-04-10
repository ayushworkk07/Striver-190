
class Solution {
    public void solveSudoku(char[][] board) {
        
        solve(board);
    }

    public boolean solve(char[][]board){
        //try every coloumn for every choice
        for(int i =0 ;i < 9 ; i++){
            for(int j = 0 ;j < 9 ; j++){

                //if empty fill with a number
                if(board[i][j] == '.'){
                    for(char number = '1'; number <= '9' ;number++){

                        //check if its valid
                        if(!isSafe(board,i,j,number)) continue;
                        
                        //mark
                        board[i][j] = (number);
                        
                        
                        if(solve(board)) return true;
                        
                        //unmark
                        board[i][j] = '.';
                            
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSafe(char[][] board , int row , int col,char number){

        for(int i = 0 ; i < 9 ; i++){
            if(board[row][i]==number) return false;
            
            if(board[i][col]==number) return false;
            
            //get 3x3 coordinates 
            if(board[3*(row/3) +(i/3)][3*(col/3) + i%3]==number) return false;
        }

        return true;
    }
}