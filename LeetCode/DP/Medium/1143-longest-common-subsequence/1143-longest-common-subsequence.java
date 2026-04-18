class Solution {
    /* each node calls further node to give their max LCS , if the currenc characters are equal 
    it adds 1 to it  and moves to i+1,j+1
    else it calls for a combination of (i,j+1) and (i+1,j)
    */
    public int longestCommonSubsequence(String text1, String text2) {
        // return f(text1,text2,0,0, new Integer[text1.length()+1][text2.length()+1]);

        return tabulization(text1,text2);
    }

    public int f(String text1 , String text2,int i , int j , Integer[][] dp){
        if(i >= text1.length() || j >= text2.length())
            return 0;

        if(dp[i][j]!=null)
            return dp[i][j];


        // if matches add 1 and call for further LCS
        if(text1.charAt(i) == text2.charAt(j)){
            return dp[i][j] = 1 + f(text1,text2,i+1,j+1,dp);
        }

        //call for further combinations and take max of them 
        return dp[i][j] = 0 + Math.max(
            f(text1,text2,i+1,j,dp),
            f(text1,text2,i,j+1,dp)
        );
    }

    public int tabulization(String text1, String text2){
        int m = text1.length() , n = text2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i = m-1; i>= 0 ;i--){
            for(int j = n-1 ; j>= 0 ;j--){

                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = 1 + dp[i+1][j+1];
                    continue;
                }

                dp[i][j] = 0 + Math.max(
                    dp[i+1][j],
                    dp[i][j+1]
                );
            }
        }

        return dp[0][0];
    }
}