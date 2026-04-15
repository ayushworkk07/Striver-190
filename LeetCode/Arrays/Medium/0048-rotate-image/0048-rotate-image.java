class Solution {
    //take transpose and then reverse each row
    public void rotate(int[][] matrix) {
        int m = matrix.length , n = matrix[0].length;

        for(int i =0 ; i < m ; i++){
            for(int j = 0 ; j <= i ;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0 ;i < m ; i++){
            reverse(matrix,i);
        }
    }

    public void reverse(int matrix[][] , int row){
        int start = 0 , end = matrix[0].length -1;

        while(start < end){
            int temp = matrix[row][start];
            matrix[row][start] = matrix[row][end];
            matrix[row][end] = temp;

            start ++;
            end --;
        }
    }
}