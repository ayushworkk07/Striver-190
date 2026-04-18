class Solution {
    TreeSet<String> set;
    public List<String> allLCS(String s1, String s2) {
        return tabulization(s1,s2);
        
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

    public List<String> tabulization(String text1, String text2){
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
        
        // 2. Optimized Reconstruction with Memoization
        Map<String, Set<String>> memo = new HashMap<>();
        Set<String> results = backtrack(dp, text1, text2, 0, 0, memo);
        
        List<String> finalResult = new ArrayList<>(results);
        Collections.sort(finalResult); // Usually required for lexicographical order
        return finalResult;
        
    }

    //memoized backtrack
    private Set<String> backtrack(int[][] dp, String s1, String s2, int i, int j, Map<String, Set<String>> memo) {
    String key = i + "," + j;
    if (memo.containsKey(key)) return memo.get(key);

    Set<String> lcss = new HashSet<>();
    // Base Case: End of strings
    if (i >= s1.length() || j >= s2.length()) {
        lcss.add("");
        return lcss;
    }

    if (s1.charAt(i) == s2.charAt(j)) {
        // If characters match, they MUST be part of the LCS
        for (String temp : backtrack(dp, s1, s2, i + 1, j + 1, memo)) {
            lcss.add(s1.charAt(i) + temp);
        }
    } else {
        // If they don't match, follow the maximum DP paths
        if (dp[i + 1][j] >= dp[i][j + 1]) {
            lcss.addAll(backtrack(dp, s1, s2, i + 1, j, memo));
        }
        if (dp[i][j + 1] >= dp[i + 1][j]) {
            lcss.addAll(backtrack(dp, s1, s2, i, j + 1, memo));
        }
    }

    memo.put(key, lcss);
    return lcss;
}
}