class Solution {
    /* add all rotten in Q with a time and run BFS and keep adding neighours and marking them inplace
    if no freshOranges are left return time else -1
    */
    int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int freshCount = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    q.add(new int[]{i, j, 0}); // {row, col, time}
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        
        if (freshCount == 0) return 0; // No fresh oranges to rot

        int maxTime = 0;
        while(!q.isEmpty()) {
            int[] pair = q.poll();
            int r = pair[0], c = pair[1], t = pair[2];
            maxTime = Math.max(maxTime, t);

            for(int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];

                // Check boundaries AND if it's a fresh orange
                if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2; // Mark as rotten (acts as visited)
                    freshCount--;
                    q.add(new int[]{nr, nc, t + 1});
                }
            }
        }

        // If freshCount > 0, some oranges were unreachable
        return freshCount == 0 ? maxTime : -1;
    }
}