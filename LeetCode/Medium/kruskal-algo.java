//Sort the array and apply DSU , if same parent skip 
class Solution {
    public static class DSU{
        int parent[];
        int size[];

        //1 based indexing
        DSU(int n){
            this.parent = new int[n+1];
            this.size = new int[n+1];

            Arrays.fill(size,1);

            for(int i = 1 ;i <= n ; i++){
                parent[i] = i;
            }
        }

        public boolean union(int u , int v){
            int pu = findParent(u);
            int pv = findParent(v);

            if(pu == pv) 
                return false;

            if(size[pu] > size[pv]){
                size[pu] += size[pv];
                parent[pv] = pu;
            }
            else{
                size[pv] += size[pu];
                parent[pu] = pv;
            }

            return true;
        }

        public int findParent(int node){
            if(parent[node] == node)
                return node;

            return parent[node] = findParent(parent[node]);
        }
    }
    static int kruskalsMST(int V, int[][] edges) {
        DSU graph = new DSU(V);

        Arrays.sort(edges,(a,b)->Integer.compare(a[2],b[2]));

        int sum = 0;
        for(int[] edge: edges){
            int u = edge[0] , v = edge[1] , wt = edge[2];

            if(graph.union(u,v))
                sum+= wt;
        }

        return sum;
    }
}