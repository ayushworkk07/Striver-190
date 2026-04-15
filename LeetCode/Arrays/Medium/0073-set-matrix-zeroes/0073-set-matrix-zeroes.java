class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1; // Flag for the first column

        // Step 1: Mark the rows and columns
        for (int i = 0; i < m; i++) {
            // Check if first column needs to be zeroed
            if (matrix[i][0] == 0) col0 = 0;
            
            // Start from second column to avoid overwriting col0 logic
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 2: Use markers to fill zeroes (avoid first row/col for now)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // Step 3: Handle the first column after the row is processed
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
    }
}
