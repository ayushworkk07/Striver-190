class Solution {
    /* might look like djikstra , but we have to apply BFS on levels
    and min among all the paths
    */
    public class Pair{
        int node;
        int wt;
        int stops;

        Pair(int node , int wt , int stops){
            this.node = node;
            this.wt = wt;
            this.stops = stops;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for(int i =0 ;i < n ;i++){
            graph.add(new ArrayList<>());

        }

        //graph
        for(int edge[] : flights){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2],0));
        }


        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src,0,0));

        int dist[] = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;

        int level = 0;
        while(!q.isEmpty()){
            Pair p = q.poll();
            int node = p.node ; int wt = p.wt; int stops = p.stops;

            if(stops > k) continue;

            for(Pair nbr : graph.get(node)){
                if(nbr.wt + wt < dist[nbr.node] ){
                    dist[nbr.node] = nbr.wt + wt;
                    q.add(new Pair(nbr.node,nbr.wt + wt,stops+1));
                }
            }
        }

        if(dist[dst] != Integer.MAX_VALUE)
            return dist[dst];

        return -1;
    }
}