class Solution {
    /* iteratively improve the shortest distance between any two nodes and 
    by checking if passing through an intermediate node provides a shorter path
    */
    public void floydWarshall(int[][] dist) {
        int m = dist.length , n = dist[0].length;

        //try all nodes as intermediates on all other nodes
        for(int k = 0 ; k < m ; k++){
            for(int u = 0 ; u < m ; u++){
                for(int v = 0 ;v < n ; v++){

                    if(dist[u][k] != Integer.MAX_VALUE && dist[k][v]!=Integer.MAX_VALUE){
                        dist[u][v] = Math.min(dist[u][v] , dist[u][k]+dist[k][v]);
                    }
                }
            }
        }

        //check negative wt cycle if diagnol element is <0

        for(int i = 0 ;i < m ; i++){
            if(dist[i][i] < 0)
                return;
        }

        return;
    }
}