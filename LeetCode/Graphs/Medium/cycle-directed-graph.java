class Solution {
    /* a cycle is detected if on the same DFS  , we get an already visited
    node on an already visited path.
    */
    public boolean isCyclic(int v, int[][] edges) {
         ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }
        for(int[]edge: edges){
            graph.get(edge[0]).add(edge[1]);
        }
        boolean [] visited = new boolean[v];
        boolean [] pathVisited = new boolean[v];
        for(int i = 0 ;i < v ; i++){
            if(visited[i] == false){
                if(dfs(graph,i,visited,pathVisited))
                    return true;
            }
        }

        return false;
    }

    public boolean dfs(ArrayList<ArrayList<Integer>> graph,int i ,boolean[] visited,boolean[]pathVisited){

        visited[i] = true;
        pathVisited[i] = true;
        
        for(int nbr: graph.get(i)){
    // Case 1: Node not visited yet -> Explore it
    if(!visited[nbr]) {
        if(dfs(graph, nbr, visited, pathVisited))
            return true;
    }
    // Case 2: Node is visited -> Check if it's on the CURRENT path
    else if(pathVisited[nbr]) {
        return true;
    }
}
        
        pathVisited[i] = false;

        return false;
    }
}