/* every node stores what will be the longest substring from itself , so a longest substring
can only be when the chars are equal or else no scene
 */
class Solution {
    int maxLCS = 0;
    public int longCommSubstr(String s1, String s2) {
        // f(s1 , s2 , 0 , 0 , new Integer[s1.length()+1][s2.length()+1]);
        tabulization(s1,s2);
        
        return maxLCS;
        
    }

    public int f (String s1 , String s2 , int i , int j , Integer[][] dp){
        if(i >= s1.length() || j>=s2.length())
            return 0;
        
        if(dp[i][j] != null)
        return dp[i][j];

        //if chars are equal we store only an answer branching from this 
        //as we need contigous
        int count = 0;
        if(s1.charAt(i) == s2.charAt(j)){
            count = 1 + f(s1,s2,i+1,j+1,dp);
            maxLCS = Math.max(maxLCS,count);
        }

        //we have to explore all the grid
        f(s1,s2,i+1,j,dp);
        f(s1,s2,i,j+1,dp);
        
        return dp[i][j] = count;
    }

    public int tabulization(String s1 , String s2){
        int m = s1.length() , n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i = m-1 ; i>=0 ; i--){
            for(int j = n-1 ; j>=0 ;j--){
                
                int count = 0;

                if(s1.charAt(i) == s2.charAt(j)){
                    count = 1 + dp[i+1][j+1];
                    maxLCS = Math.max(maxLCS,count);
                }

                dp[i][j] = count;
            }
        }

        return dp[0][0];
    }
}