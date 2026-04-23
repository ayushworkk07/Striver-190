class Solution {
    /* run BFS with priority Q on edges wt from 0th node and find the minimum edges 
    for each nodes such that sum minimizes. 
    -> very important to mark visited when polling to explore all the edges
    */
    public class Pair{
        int node , parent , wt;

        Pair(int node , int parent , int wt){
            this.node = node; this.parent = parent ; this.wt = wt;
        }
    }
    public int spanningTree(int V, int[][] edges) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> Integer.compare(a.wt,b.wt));
        boolean visited[] = new boolean[V];

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for(int i =0 ;i < V ; i++){
            graph.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            int u = edge[0] , v = edge[1] , wt= edge[2];

            graph.get(u).add(new Pair(v,-1,wt));
            graph.get(v).add(new Pair(u,-1,wt));
        }

        pq.add(new Pair(0,-1,0));

        int sum = 0;
        List<int[]> newEdges = new ArrayList<>();
        
        while(!pq.isEmpty()){
            Pair p = pq.poll();

            int node = p.node, parent = p.parent , wt = p.wt;

            if(visited[node])continue;

            //we mark on poll so every edge can be explored , if found smaller later
            visited[node] = true;

            if(parent != -1){
                newEdges.add(new int[]{node,parent,wt});
                sum+= wt;
            }

            for(Pair nbr : graph.get(node)){
                if(!visited[nbr.node])
                pq.add(new Pair(nbr.node,node,nbr.wt));
            }
        }

        return sum;

    }
}