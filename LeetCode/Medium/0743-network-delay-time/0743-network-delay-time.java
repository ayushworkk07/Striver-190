
class Solution {
    /* run djikstra from source node and find min distance to all , return max of them
    */
    public class Pair{
        int node;
        int wt;

        Pair(int node , int wt ){
            this.node = node;
            this.wt = wt;
        }
    }
    public int networkDelayTime(int[][] times, int n, int src) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        //1 based indexing
        for(int i =0 ;i <= n ;i++){
            graph.add(new ArrayList<>());

        }

        //graph
        for(int edge[] : times){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
        }


        PriorityQueue<Pair> q = new PriorityQueue<>((a,b)-> Integer.compare(a.wt,b.wt));
        q.add(new Pair(src,0));

        int dist[] = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;


        while(!q.isEmpty()){
            Pair p = q.poll();
            int node = p.node , wt = p.wt;

            for(Pair nbr : graph.get(node)){
                int newWt = wt+nbr.wt;

                if(newWt < dist[nbr.node]){
                    dist[nbr.node] = newWt;
                    q.add(new Pair(nbr.node,newWt));
                }
            }
        }

        int ans = Integer.MIN_VALUE;

        for(int i = 1 ;i <= n ; i++){
            ans = Math.max(ans,dist[i]);
        }

        if(ans == Integer.MAX_VALUE)
            return -1;

        return ans;
    }
}