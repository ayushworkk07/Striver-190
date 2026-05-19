class Solution {
    /* use BFS and find shortes path to each nodes in waves
    */
    public int[] shortestPath(int v, int[][] edges, int src) {

         ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }
        for(int[]edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] dist = new int[v];
        Arrays.fill(dist,-1);

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(src);    
        dist[src] = 0;

        while(!q.isEmpty()){
            int node = q.poll();
            int d = dist[node];
            
            for(int nbr : graph.get(node)){
                if(dist[nbr] == -1){                        // unvisited
                    dist[nbr] = d+1;
                    q.offer(nbr);
                }
            }
        }

        return dist;
    }
}

// User function Template for Ja