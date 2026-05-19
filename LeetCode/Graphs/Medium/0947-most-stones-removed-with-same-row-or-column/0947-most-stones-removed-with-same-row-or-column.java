class Solution {
    /* every row,col is a node to differentiate between , we add an offset of 1001 to cols
    and make an edge between row , col . if any node has a row or col common 
    they are gonna get the same ultimate parent and hence can be grouped together

    -> in a group of size n , you can pick up n-1 stones and leave 1 stone remaning
    -> ans = total stones - groups
    */
    public static class DSU{
        int parent[];
        int size[];

        //1 based indexing
        DSU(int n){
            this.parent = new int[n];
            this.size = new int[n];

            Arrays.fill(size,1);

            for(int i = 0 ;i < n ; i++){
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
    public int removeStones(int[][] stones) {
        int n = (int)1e4*2 + 2;
        int offset = (int)1e4 +1;

        DSU graph = new DSU(n);
        HashSet<Integer> uniqueNodes = new HashSet<>();

        for(int edge[] : stones){
            int row = edge[0] ; int col = edge[1] + offset;
            graph.union(row,col);

            uniqueNodes.add(row);
            uniqueNodes.add(col);
        }

        int count = 0;
        for(int node : uniqueNodes){
            if(graph.parent[node] == node)
                count++;
        }

        return stones.length - count;

    }
}