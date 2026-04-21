
class Solution {
    /* we apply djikstra and store another array ways[] , which stores no. of ways to reach 
    that node
    -> if we find a shorter path we overwrite ways[v] = ways[u];
    -> if we find an equal path we add ways[v] += ways[u];
    ways[src] = 1;
    ->This is exactly like Dynamic Programming. 
    Each node stores how many ways it can be reached via the shortest path.
    */
    public class Pair{
        int node;
        long wt;                    // make wt into long

        Pair(int node , long wt ){
            this.node = node;
            this.wt = wt;
        }
    }
    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        long mod = (long)1e9 + 7;

        for(int i =0 ;i < n ;i++){
            graph.add(new ArrayList<>());

        }

        //graph
        for(int edge[] : roads){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
            graph.get(edge[1]).add(new Pair(edge[0],edge[2]));
        }


        PriorityQueue<Pair> q = new PriorityQueue<>((a,b)-> Long.compare(a.wt,b.wt));
        q.add(new Pair(0,0));

        long dist[] = new long[n];
        long ways[] = new long[n];

        Arrays.fill(dist,Long.MAX_VALUE);   //use long.max_value
        dist[0] = 0;
        ways[0] = 1;


        while(!q.isEmpty()){
            Pair p = q.poll();
            int u = p.node ;
            long uwt = p.wt;

            // Optimization: Skip outdated pairs
            if (uwt > dist[u]) continue; 


            for(Pair nbr : graph.get(u)){
                int v = nbr.node;
                long vwt = uwt+nbr.wt;

                //if found shorter path
                if(vwt < dist[v]){

                    dist[v] = vwt;
                    ways[v] = ways[u];
                    q.add(new Pair(v,vwt));
                }
                //if equal paths , add it
                else if(vwt == dist[v]){
                    ways[v] = (ways[v] + ways[u])%mod;
                }
            }
        }

        return (int)ways[n-1];
    }
}