class Solution {
    /* topological sort the nodes -> you get the nodes in the order
    they can be traversed , make dist[src] = 0 , keep popping out from the 
    stack and relax the nbr nodes.
    */
    public class Pair{
        int node;
        int wt;

        Pair(int node , int wt){
            this.node = node;
            this.wt = wt;
        }
    }
    public int[] shortestPath(int v, int e, int[][] edges) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for(int i =0 ;i < v ;i++){
            graph.add(new ArrayList<>());

        }

        //graph
        for(int edge[] : edges){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
        }
        
        //topo sort
        Stack<Integer> stack = new Stack<>();
        boolean[]visited = new boolean[v];

        for(int i =0 ;i < v ;i++){
            if(!visited[i])
                dfs(graph,i,stack,visited);
        }

        int dist[] = new int[v];
        Arrays.fill(dist,(int)1e9);
        dist[0] = 0;

        //relax edges
        while(!stack.isEmpty()){
            int node = stack.pop();

            if(dist[node] != (int)1e9){
                for(Pair nbr : graph.get(node)){
                    dist[nbr.node] = Math.min(dist[nbr.node], dist[node] + nbr.wt);
                }
            }
        }

        for(int i =0 ;i < v ; i++){
            if(dist[i] == (int)1e9)
                dist[i] = -1;
        }

        return dist;
        
    }

    public void dfs(ArrayList<ArrayList<Pair>> graph , int root , Stack<Integer> stack , boolean visited[]){

        visited[root] = true;
        for(Pair nbr : graph.get(root)){
            if(!visited[nbr.node])
                dfs(graph,nbr.node,stack,visited);
        }

        stack.push(root);
    }
}