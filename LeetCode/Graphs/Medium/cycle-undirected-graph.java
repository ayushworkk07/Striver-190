class Solution {
    /* run dfs/bfs with (node,parent) , if any node has a nbr which is visited and not parent
    there is a cycle and its a repeated node.
    */
    public boolean isCycle(int v, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }
        for(int[]edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean [] visited = new boolean[v];
        for(int i = 0 ;i < v ; i++){
            if(visited[i] == false){
                // if(dfs(graph,i,-1,visited))
                // return true;

                if(bfs(graph,i,visited))
                    return true;
            }
        }

        return false;
    }

      public boolean dfs(ArrayList<ArrayList<Integer>> graph , int node ,int parent, boolean[] visited){

        visited[node] = true;

        for(int nbr : graph.get(node)){
            if(visited[nbr] == false){
                if(dfs(graph,nbr,node,visited))
                return true;
            }
            else if(nbr != parent)
                return true;
        }

        return false;
    }   

    public boolean bfs(ArrayList<ArrayList<Integer>> graph , int root, boolean[] visited){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{root,-1});
        visited[root] = true;

        while(!q.isEmpty()){
            int[] pair = q.poll();

            int node = pair[0] , parent = pair[1];

            for(int nbr : graph.get(node)){
                if(!visited[nbr]){
                    visited[nbr] = true;
                    q.add(new int[]{nbr,node});
                }
                else if(nbr != parent)
                    return true;
            }
        }

        return false;
    }
}