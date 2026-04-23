/* works like BFS but with PQ , so less weighted nodes come out first
keep a dist[] with 1e9 initialized , make dist[src] = 0 , 
add src in pq, and keep relaxing nodes and filling dist array
*/
class Solution {
    public class Pair{
        int node;
        int wt;

        Pair(int node , int wt){
            this.node = node;
            this.wt = wt;
        }
    }
    public int[] dijkstra(int v, int[][] edges, int src) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }

        //graph
        for(int edge[] : edges){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
            graph.get(edge[1]).add(new Pair(edge[0],edge[2]));
        }

        int dist[] = new int[v];
        Arrays.fill(dist,(int)1e9);
        dist[src] = 0;

        //add in PQ 
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            return Integer.compare(a.wt,b.wt);
        });

        pq.add(new Pair(src,0));

        //BFS
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int root = p.node , wt = p.wt;

            for(Pair nbr : graph.get(root)){
                int nbrNode = nbr.node , nbrWt = nbr.wt;
                int pathWt = nbrWt + wt;

                //if smaller distance found
                if(pathWt < dist[nbrNode]){
                    dist[nbrNode] = pathWt;
                    pq.offer(new Pair(nbrNode,pathWt));
                }
            }
        }

        return dist;
    }
}