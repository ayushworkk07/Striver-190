class Solution {
    /* use 2 colours to paint the graph such that 2 adjacent nodes doesnt have the same
    colour in BFS
    */
    public boolean isBipartite(int[][] graph) { 
        int v = graph.length;

        int[] visited = new int[v];

        for(int i = 0 ; i < v ; i++){
            if(visited[i]== 0 ){

                // if(bfs(graph,i,visited) == false)
                //     return false;

                if(dfs(graph,i,visited,1) == false)
                    return false;
            }

        }

        return true;
    }

    public boolean bfs(int[][] graph,int i ,int[] visited){
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(i);
        visited[i] = 1;

        while(q.size() != 0){
            int node = q.poll();

            for(int nbr : graph[node]){
                if(visited[nbr] == 0){
                    visited[nbr] = visited[node] == 1 ? 2 : 1;
                    q.add(nbr);
                }

                else if(visited[nbr] == visited[node])
                    return false;
            }   
        }

        return true;
    }

    public boolean dfs(int[][] graph,int i ,int[] visited,int color){

        visited[i] = color;

        for(int nbr: graph[i]){
            if(visited[nbr] == 0){
                    int newColor = color == 1 ? 2 : 1;
                    if(dfs(graph,nbr,visited,newColor) == false)
                    return false;
            }

            else if(visited[nbr] == color)
                return false;
        }

        return true;
    }
}