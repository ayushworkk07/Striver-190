/*  find LPS and return n - LPS , as making non pallindrome characters into LPS is the minimum
it will take , keeping the LPS untouched
*/
class Solution {
    public int minInsertions(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        int longestPalindromeSubseq = tabulization(s,reverse);

        return s.length() - longestPalindromeSubseq;
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