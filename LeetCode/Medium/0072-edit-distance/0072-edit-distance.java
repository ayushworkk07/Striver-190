/*  each node(combination of i ,j) returns the minimum number of operations needed to convert 
    substring of s1 (i to n) to substring of s2(j to n)

    if chars already equal at i , j . we ask further nodes for their minimum and add 0
    0 + f(i+1,j+1)

    else
        we try all operations here 
    deleting - 1+ f(i+1,j)      //next character i
    inserting - 1+ f(i, j+1)     //j is matched
    replacing - 1+ f(i+1,j+1)     // both are matched
*/
class Solution {
    public int minDistance(String word1, String word2) {
        // return f(word1 ,word2 , 0 ,0 , new Integer[word1.length()][word2.length()]);

        return tabulization(word1,word2);
    }

    public int f(String word1, String word2, int i , int j , Integer[][] dp){
        // Base Case: If s1 is empty, must INSERT all remaining s2
        if (i == word1.length()) return dp[i][j] = word2.length() - j;
        // Base Case: If word2 is empty, must DELETE all remaining word1
        if (j == word2.length()) return dp[i][j] = word1.length() - i;

        if(dp[i][j] != 0)
            return dp[i][j];

        if(word1.charAt(i) == word2.charAt(j)){
            return dp[i][j]= 0 + f(word1,word2,i+1,j+1,dp);
        }

        else{
            int insert = 1 + f(word1,word2,i,j+1,dp);
            int delete = 1 + f(word1,word2,i+1,j,dp);
            int replace = 1 + f(word1,word2,i+1,j+1,dp);

            return dp[i][j]= Math.min(insert,Math.min(delete,replace));
        }
    }

    public int tabulization(String word1, String word2){
        int m = word1.length() , n = word2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = m ; i>=0 ;i--){

            for(int j = n ; j>=0 ; j--){

                if (i == word1.length()){
                    dp[i][j] = word2.length() - j;
                    continue;
                }

                if (j == word2.length()){
                    dp[i][j] = word1.length() - i;
                    continue;
                }

                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i][j]= 0 + dp[i+1][j+1];
                }

                else{
                    int insert = 1 + dp[i][j+1];
                    int delete = 1 + dp[i+1][j];
                    int replace = 1 + dp[i+1][j+1];

                    dp[i][j]= Math.min(insert,Math.min(delete,replace));
                }

            }
        }

        return dp[0][0];
    }
}