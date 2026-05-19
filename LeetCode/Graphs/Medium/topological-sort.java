//also included cycle detection which is not needed if given its a DAG
class Solution {
    boolean isCycle;
    public ArrayList<Integer> topoSort(int v, int[][] edges) {

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }
        for(int[]edge: edges){
            graph.get(edge[0]).add(edge[1]);
        }

        //kahn's algo 
        if(true)
        return bfs(v,graph);

        boolean [] visited = new boolean[v];
        boolean[] pathVisited = new boolean[v];

        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0 ; i < v ; i++){
            if(visited[i] == false)
                dfs(graph,i,visited,pathVisited,stack);
        }

        ArrayList<Integer> list = new ArrayList<>();

        while(!stack.isEmpty())
            list.add(stack.pop());

        return list;
    }

    public boolean dfs(ArrayList<ArrayList<Integer>> graph,int i ,boolean[] visited,boolean[] pathVisited, Stack<Integer> stack){

        visited[i] = true;
        pathVisited[i] = true;

        for(int nbr: graph.get(i)){
            if(!visited[nbr]){
                if(dfs(graph,nbr,visited,pathVisited,stack) == false)
                    return false;
            }
            //detecting cycle
            else if(pathVisited[nbr] == true)
                return false;
        }

        pathVisited[i] = false;
        stack.push(i);
        return true;
    }

    //kahns algo
    public ArrayList<Integer> bfs(int v, ArrayList<ArrayList<Integer>> graph){
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

        ArrayList<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);

            for(int nbr : graph.get(node) ){
                indegree[nbr]--;
                
                if(indegree[nbr] == 0)
                    q.add(nbr);
            }
        } 

        //cycle detected
        if(ans.size() < v) return new ArrayList<Integer>();

        return ans;
            
    }
}