class Solution {
    /* each node (combination of i , j) returns total number of distinct subsequences of substring 
    of s (i to n)  which are equal to t (j to n)

    if the chars are equal it asks the next nodes to solve themselves by 
    1) finding the next equal characters i+1,j+1
    2) finding another equal character i+1, j

    if not equal chars , it asks the next node to solve themselves by
    1)finding the next equal character (i+1,j)
    */
    public int numDistinct(String s, String t) {
        // return f(s,t,0,0, new Integer[s.length()+1][t.length()+1]);
        return tabulization(s,t);
    }

    public int f(String s , String t , int i , int j , Integer[][] dp ){
        if(j >= t.length())
            return dp[i][j] = 1;

        if(i >= s.length())
            return dp[i][j] = 0;

        if(dp[i][j] != null)
            return dp[i][j];
        
        int count = 0;
        if(s.charAt(i) == t.charAt(j)){
            count += f(s,t,i+1,j+1,dp) + f(s,t,i+1,j,dp);
        }

        else{
            count += f(s,t,i+1,j,dp);
        }

        return dp[i][j] = count;
    }

    public int tabulization(String s, String t){
        int m = s.length() , n = t.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = m ; i>= 0 ;i--){
            for(int j = n ; j>= 0 ; j--){
                if(j >= t.length()){
                    dp[i][j] = 1;
                    continue;
                }

                if(i >= s.length()){
                    dp[i][j] = 0;
                    continue;
                }

                int count = 0;

                if(s.charAt(i) == t.charAt(j))
                    count = dp[i+1][j+1] + dp[i+1][j];

                else
                    count = dp[i+1][j];

                dp[i][j] = count;
            }
        }

        return dp[0][0];
    }
}
