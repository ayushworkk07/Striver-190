class Solution {
    /*  this is just front partitioning where f(i) denotes the minimum partition needed such that
    i till n substring is a pallindrome . 
    
    => we try all cuts from [i,j] and check if s[i,k] is a pallindrome that is our 1 cut and 
    then find min cuts for f(k+1)
    */
    public int minCut(String s) {
        int n = s.length();


        return f(s,0 , new Integer[n+1]);
        // return tabulization(s);
    }

    public int f(String s, int start, Integer[] dp){
        int n = s.length();

        if(start == n)
        return dp[start] =  0;

        if(dp[start] != null)
            return dp[start];

        int min = (int)1e5;
        for(int k = start  ; k <= n-1 ;k++){
            if(isPalindrome(s,start,k) ){
                int cost  = 0;

                //reached end of the string , the whole string is palindrome we dont need cuts
                if(k != n-1){
                    cost = 1 + f(s,k+1,dp);
                }

                min = Math.min(min , cost);
            }
        }

        return dp[start] =  min;
    }

    public int tabulization(String s){
        int n = s.length();
        int[] dp = new int[n+1];

        for(int start = n ; start >= 0 ; start--){

            if(start == n){
                dp[start] =  0;
                continue;
            }

            int min = (int)1e5;
            for(int k = start  ; k <= n-1 ;k++){
                if(isPalindrome(s,start,k) ){
                    int cost = 0;

                    if(k!= n-1){
                        cost = 1+ dp[k+1];
                    }
                    min = Math.min(min , cost);
                }
            }

            dp[start] =  min;
        }

        return dp[0];
    }

    public boolean isPalindrome(String s, int start , int end){
        while(start <= end){
            if(s.charAt(start) != s.charAt(end))
                return false;
            
            start++;
            end--;
        }

        return true;
    }
}