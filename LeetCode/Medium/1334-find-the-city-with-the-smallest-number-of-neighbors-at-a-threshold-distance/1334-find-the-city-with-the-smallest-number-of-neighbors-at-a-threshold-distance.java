class Solution {
    /* apply floyd marshall to find all shortest distance from all cities
    add the cities with the lowest count
    */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dist[][] = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for(int edge[]: edges){
            int u = edge[0],v=edge[1],wt=edge[2];

            dist[u][v] = wt;
            dist[v][u] = wt;
        }


        for(int k = 0 ;k < n ;k++){
            for(int u = 0 ; u < n ; u++){
                for(int v = 0 ;v< n ; v++){
                    if(dist[u][k] != Integer.MAX_VALUE && dist[k][v] != Integer.MAX_VALUE){
                        int newDist = dist[u][k] + dist[k][v];

                        dist[u][v] = Math.min(dist[u][v],newDist);
                    }
                }
            }
        }

        int ans = -1;
        int ansCount = Integer.MAX_VALUE;

        for(int i = 0; i < n ; i++){
            int count = 0;
            for(int j = 0 ;j < n ; j++){
                if(i!=j && dist[i][j] <= distanceThreshold)
                    count++;
            }

            if(count <= ansCount){
                ansCount = count;
                ans = i;
            }
        }

        return ans;
    }
}