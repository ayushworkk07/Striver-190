class Solution {
    // apply topo sort
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int v = numCourses;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }
        for(int[]edge: prerequisites){
            graph.get(edge[1]).add(edge[0]);
        }

        return bfs(v,graph);
    }

    public int[] bfs(int v ,ArrayList<ArrayList<Integer>> graph ){
        int[] indegree = new int[v];

        for(int i = 0 ;i < v ; i++){
            for(int node : graph.get(i))
                indegree[node]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for(int i =0 ;i < v ; i++){
            if(indegree[i] == 0)
                q.add(i);
        }

        int index = 0;
        int ans[] = new int [v];

        while(!q.isEmpty()){
            int node = q.poll();
            ans[index++] = node;

            for(int nbr : graph.get(node)){
                indegree[nbr]--;
                if(indegree[nbr] == 0)
                    q.add(nbr);
            }
        }

        if(index < v) return new int[0];

        return ans;
    }
}