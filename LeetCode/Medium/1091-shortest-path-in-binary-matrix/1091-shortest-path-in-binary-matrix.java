class Solution {
    /* apply djikstra from 0,0 coloum till n-1,m-1
    */
    public class Pair{
        int row;
        int col;
        int count;

        Pair(int row , int col,int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    int dir[][] = {{0,1},{1,0},{1,1},{0,-1},{-1,0},{-1,-1},{-1,+1},{1,-1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length , n = grid[0].length;
        if(grid[0][0] != 0) return -1;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.count,b.count));
        pq.add(new Pair(0,0,0));

        //dist[src] =0
        int dist[][] = new int[m][n];
        dist[0][0] = 1;
        
        for(int i =0 ;i < m ; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int rr = p.row , rc = p.col , rcount = p.count;
            
            //if found < distance than orignal only then update and call further
            if(rcount +1 < dist[rr][rc]){
                dist[rr][rc] = rcount+1;

                for(int dir[] : dir){
                    int nr = rr + dir[0];
                    int nc = rc + dir[1];

                    if(nr < m && nc < n && nr >= 0 && nc >= 0&& grid[nr][nc] == 0){
                        pq.add(new Pair(nr,nc,rcount+1));
                    }
                }
            }

        }

        if(dist[m-1][n-1] != Integer.MAX_VALUE)
        return dist[m-1][n-1];

        return -1;
    }
}