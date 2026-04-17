class Solution {
    /* f(i,j) returns the minimum cost for a stick with cuts[i->j]
    1)we add 0 and n to the list so that we can compute the length starting with n - 0
    2) and we process the partion dp for i= 0+1 to j= n-1 cuts 
    so we always have the length between 2 cuts as cuts[j+1] - cuts[i-1]

    ** we apply partition dp on cuts[] and not on length of stick
    */
    public int minCost(int n, int[] cuts) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int c: cuts)list.add(c);
        
        list.add(0);
        list.add(n);

        int m = list.size();
        Collections.sort(list);

        return f(list,0,list.size()-1 , new Integer[m][m]);
    }

    public int f(ArrayList<Integer> cuts, int start , int end , Integer[][]dp){
        if(end - start <=1)
            return dp[start][end] = 0;

        if(dp[start][end] != null)
            return dp[start][end];
        
        int length = cuts.get(end)-cuts.get(start);
        int minCost = Integer.MAX_VALUE;
        for(int k = start+1 ; k <= end-1 ; k++){

                int cost = length + f(cuts,start,k,dp) + f(cuts,k,end,dp);

                minCost = Math.min(minCost,cost);
        }

        return dp[start][end] = minCost;
    }
}