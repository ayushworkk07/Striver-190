/*  each node(combination of i ,j) returns if substring s1(i->n) can match s2(j->m)

    at each node we check if chars are equal and all combination of operations
    and call further node for their answer 

    base case becomes when either pattern is exhausted or string is exhausted
*/
class Solution {
    public boolean isMatch(String s, String p) {
        // return f(s,p,0,0 , new Boolean[s.length()+1][p.length()+1]);

        return tabulization(s,p);
    }

    public boolean f(String s, String p , int i , int j , Boolean[][]dp){

        // 1. Base Case: Pattern is exhausted
        if (j == p.length()) {
            return dp[i][j] = (i == s.length());
        }

        // 2. Base Case: String is exhausted
        if (i == s.length()) {
            // Remaining pattern must be all '*' to match empty string
            if (p.charAt(j) == '*') {
                return dp[i][j] = f(s, p, i, j + 1, dp);
            }
            return dp[i][j] = false;
        }

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            return dp[i][j] = f(s,p,i+1,j+1,dp);
        }

        // Choice A: Skip '*' (match empty) -> f(i, j+1)
        // Choice B: Use '*' to match current s[i] and stay at j -> f(i+1, j)
        if(p.charAt(j) == '*'){
            return dp[i][j] = f(s,p,i+1,j,dp)  || f(s,p,i,j+1,dp);
        }

        //will never go to this , remove from tabulization
        return dp[i][j] = false;
    }

    public boolean tabulization(String s, String p){
        int m = s.length() , n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];

        for(int i = m ; i>= 0 ; i--){
            for(int j = n ;j>=0 ; j--){
                if(j == p.length()){
                    dp[i][j] = (i == s.length());
                    continue;
                }

                if (i == s.length()) {
                    // Remaining pattern must be all '*' to match empty string
                    if (p.charAt(j) == '*') 
                    dp[i][j] = dp[i][j+1];
                    
                    else
                    dp[i][j] = false;

                    continue;
                }

                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    dp[i][j] = dp[i+1][j+1];
                }

                if(p.charAt(j) == '*'){
                    dp[i][j] = dp[i+1][j] || dp[i][j+1];
                }

            }
        }

        return dp[0][0];
    }
}