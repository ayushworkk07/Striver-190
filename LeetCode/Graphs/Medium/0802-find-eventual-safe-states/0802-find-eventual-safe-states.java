class Solution {
    /* we need to find nodes which are terminal or all paths lead to terminal node
    1) reverse the graph , terminal node becomes node with indegree = 0;
    2)kahn's algo works on removing the outdegree edges from indegree nodes
    3)so essentially we are covering all paths from indegree = 0 nodes to other
    4) so we reverse the graph and add all these nodes which comes in the path
    5) which is kahn's algo itself after reversing
    */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int v = graph.length;
        ArrayList<ArrayList<Integer>> reverseGraph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            reverseGraph.add(new ArrayList<>());

        }
        //reverse the graph
        for(int i = 0 ;i < v; i++){
            int edges[] = graph[i];

            for(int j = 0 ; j < edges.length ; j++){
                reverseGraph.get(edges[j]).add(i);
            }
        }

        //run kahn's algo to add all nodes , whose path are leading to indegree = 0 nodes
        return bfs(v,reverseGraph);
    }
    public List<Integer> bfs(int v, ArrayList<ArrayList<Integer>> graph){
        //calculate indegree
        int[] indegree = new int[v];

        for(int i = 0 ;i < v ; i++){
            for(int node : graph.get(i))
                indegree[node]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        //add all 0 indegrees
        for(int i = 0 ;i < v ; i++){
            if(indegree[i]==0)
            q.offer(i);
        }

        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);

            for(int nbr : graph.get(node) ){
                indegree[nbr]--;
                
                if(indegree[nbr] == 0)
                    q.add(nbr);
            }
        } 

        Collections.sort(ans);
        return ans;
            
    }
}