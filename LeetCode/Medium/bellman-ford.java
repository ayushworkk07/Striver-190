class Solution {
    /* for every node max edges can be v-1 , so we can find optimized value in v-1 iterations
    if even after v-1 iterations , any d[v] is decreasing , there is a negative wt cycle
    */
    public int[] bellmanFord(int v, int[][] edges, int src) {

        int[] dist = new int[v];
        Arrays.fill(dist,(int)1e8);
        dist[src] = 0;

        //relax edges for v-1 iterations
        for(int i = 0 ;i < v-1 ;i++){
            relaxEdges(edges,dist,false);
        }

        //if any node is relaxed in vth iteration we have found a -ve cycle
        if(relaxEdges(edges,dist,true))
            return new int[]{-1};

        return dist;
    }

    public boolean relaxEdges(int[][] edges,int[] dist, boolean isVthIteration){
        for(int edge[] : edges){
            int u = edge[0] , v = edge[1] , wt = edge[2];
            
            //negative wt will make dist[v] < Integer.MAX_VALUE
            if(dist[u]!= (int)1e8 && dist[v] > dist[u] + wt){
                if(isVthIteration) return true;
                dist[v] = dist[u] + wt;
            }
        }

        return false;
    }
}