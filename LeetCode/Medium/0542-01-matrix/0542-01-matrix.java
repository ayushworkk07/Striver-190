class Solution {
    /* multi source BFS , add all 0's with distance 0 and keep finding near by 1 
     -> change them into 0 and make their distance as distance +1
    */
        int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length , n = mat[0].length;
        int dist[][] = new int[m][n];       //distance

        for(int i = 0 ;i < m ; i++)
        Arrays.fill(dist[i],-1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i =0 ;i < m ; i++){
            for(int j = 0 ;j < n ; j++){
                if(mat[i][j] == 0){
                    dist[i][j] = 0;
                    q.offer(new int[]{i,j,0});
                }
            }
        }

        while(!q.isEmpty()){
            int pair[] = q.pop();
            int r = pair[0] , c = pair[1] , distance = pair[2];

            for(int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];

                if(nr >= 0 && nr < m && nc >= 0 && nc < n && mat[nr][nc] == 1) {
                    mat[nr][nc] = 0;
                    dist[nr][nc] = distance+1;
                    q.add(new int[]{nr, nc, distance + 1});
                }
            }
        }

        return dist;
    }
}