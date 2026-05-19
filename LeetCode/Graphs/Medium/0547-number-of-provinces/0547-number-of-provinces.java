class Solution {
    /*  run DFS from each node , return no. of connected components
    */
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int v = isConnected.length;

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }
        for(int i = 0 ;i < v;  i++){
            int edge[] = isConnected[i];

            for(int j = 0 ;j < v ; j++){
                if(j != i && edge[j] == 1)
                graph.get(i).add(j);
            }
        }

        int visited[] = new int[v];

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for(int i = 0 ;i < v ; i++){
            if(visited[i]!=1){
                ArrayList<Integer> current = new ArrayList<>();
                dfs(graph , i , new ArrayList<>(),visited , ans);
                ans.add(current);
            }
        }

        return ans.size();
    }

    public void dfs(ArrayList<ArrayList<Integer>> graph , int vertex ,ArrayList<Integer> current, int[] visited ,ArrayList<ArrayList<Integer>> ans ){
        //already visited
        if(visited[vertex] == 1)
            return ;

        current.add(vertex);
        visited[vertex] = 1;

        for(int nbr : graph.get(vertex)){
            if(visited[nbr]==0)
                dfs(graph,nbr,current,visited,ans);
        }
    }
}