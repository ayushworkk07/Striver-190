class Solution {
    /* apply djikstra on absolute difference of coloumn values
    */
    public class Pair{
        int row;
        int col;
        int diff;

        Pair(int row , int col,int diff){
            this.row = row;
            this.col = col;
            this.diff = diff;
        }
    }
     int dir[][] = {{0,1},{1,0},{0,-1},{-1,0}};
    public int minimumEffortPath(int[][] grid) {
        int m = grid.length , n = grid[0].length;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.diff,b.diff));
        pq.add(new Pair(0,0,0));

        //dist[src] =0
        int dist[][] = new int[m][n];
        
        for(int i =0 ;i < m ; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        dist[0][0] = 0;

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int rr = p.row , rc = p.col , prevDiff = p.diff;
            
            //if found < distance than orignal only then update and call further    
            if(rr == m-1 && rc == n-1)
            return prevDiff;

            if(prevDiff > dist[rr][rc]) continue;

            dist[rr][rc] = prevDiff;

            for(int dir[] : dir){
                int nr = rr + dir[0];
                int nc = rc + dir[1];

                if(nr < m && nc < n && nr >= 0 && nc >= 0){
                    int newDiff = Math.max(prevDiff , Math.abs(grid[nr][nc] - grid[rr][rc]));
                    
                    if(newDiff < dist[nr][nc]){
                        pq.add(new Pair(nr,nc,newDiff));
                    }
                }
            }

        }

        return dist[m-1][n-1];
    }
}