class Solution {
    /* every mulitplication is 1 unit , therefore treat this as djikstra with counting steps
    where nodes can be from start(1) all the way to 1e5(mod)
    */
    int mod = 100000;
    public class Pair{
        int node ;
        int wt;

        Pair(int node , int wt){
            this.node = node;
            this.wt = wt;
        }
    }
    public int minimumMultiplications(int[] arr, int start, int end) {
        int dist[] = new int[mod];
        Arrays.fill(dist,Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.wt,b.wt));
        pq.add(new Pair(start,0));


        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int u = p.node , uwt = p.wt;

            if(u == end)
                return uwt;

            if(uwt > dist[end]) continue;
            
            //nbr nodes will get after multiplying
            for(int val : arr){
                int v = (val*u)%mod;

                if(v < dist[v]){
                    dist[v] = v;
                    pq.add(new Pair(v,uwt+1));
                }
            }
        }
        
        //not able to reach end
        return -1;
    }

    
}