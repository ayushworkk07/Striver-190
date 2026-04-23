import java.util.Arrays;

/* turn 2d array into 1d , union the nodes and keep a count of islands
add +1 when you mark it 1 , keep reducing count as you keep connecting 
nodes
*/
public class Solution {
    public static class DSU{
        int parent[];
        int size[];

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

    public static int[] numOfIslandsII(int n, int m, int[][] q) {
        //turning into 1d array
        DSU graph = new DSU(n*m);
        int ans[] = new int[q.length];
        int grid[] = new int[n*m];
        int index = 0;

        int[][] dirs = new int[][]{
    {0,1},{0,-1},{-1,0},{1,0}
};

        int countIsland = 0;
        for(int query[] : q){
            
            int qr = query[0] , qc = query[1];
            int node = getIndex(qr,qc,m);

            //duplicate handling
            if(grid[node] == 1){
                ans[index++] = countIsland;
                continue;
            }

            grid[node] = 1;     //mark and increase countOfIsland
            countIsland++;

            for(int dir[] : dirs){
                int nr = qr + dir[0] , nc = qc + dir[1];

                //check nbrs and connect islands and reduce totalCount
                if(nr >= 0 && nc >= 0 && nr < n && nc < m && grid[getIndex(nr,nc,m)] == 1){
                    int nbrNode = getIndex(nr,nc,m);
                        if(graph.union(node,nbrNode))
                            countIsland--;
                }
            }

            ans[index] = countIsland;
            index++;
        }

        return ans;
    }

    public static int getIndex(int row , int col , int totalCols){
        return row*totalCols + col;
    }
}