class Solution {
    /* BFS from given cell , keep changing orignalColour to colour to mark them
    */
    int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length , n = image[0].length;
        int orignal = image[sr][sc];
        image[sr][sc] = color;

        boolean[][] visited = new boolean[m][n];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr,sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()){
            int pair[] = q.pop();
            int row = pair[0] , col = pair[1];

            for(int[] d : dir) {
                int nr = row + d[0], nc = col + d[1];

                // Check boundaries AND if it's a fresh orange
                if(nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == orignal && visited[nr][nc] == false) {
                    image[nr][nc] = color; // Mark as rotten (acts as visited)
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }

        }

        return image;
    }
}