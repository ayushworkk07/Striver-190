/*  the question is similiar to LIS , if we sort all the words and then 
add a condition to check predecessor 
*/
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());


        return tabulization(words);
    }

    public int f(String[] words, int index, int lastIndex, Integer[][] dp) {
        if (index == words.length) return 0;

        if (dp[index][lastIndex + 1] != null) return dp[index][lastIndex + 1];

        // Choice 1: Not Pick
        int notPick = f(words, index + 1, lastIndex, dp);

        // Choice 2: Pick (Only if it extends the string chain)
        int pick = 0;
        if (lastIndex == -1 || isPredecessor(words[lastIndex], words[index])) {
            pick = 1 + f(words, index + 1, index, dp);
        }

        return dp[index][lastIndex + 1] = Math.max(pick, notPick);
    }

    public int tabulization(String[] words) {
        int n = words.length;

        int[][] dp = new int[n + 1][n + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int lastIndex = index - 1; lastIndex >= -1; lastIndex--) {
                
                // Choice 1: Not Pick
                int notPick = dp[index + 1][lastIndex + 1];

                // Choice 2: Pick
                int pick = 0;
                if (lastIndex == -1 || isPredecessor(words[lastIndex], words[index])) {
                    pick = 1 + dp[index + 1][index + 1]; // Use index+1 offset
                }

                dp[index][lastIndex + 1] = Math.max(pick, notPick);
            }
        }

        return dp[0][0]; // Start at index 0 with lastIndex -1
    }

    private boolean isPredecessor(String prev, String curr) {
        if (prev.length() + 1 != curr.length()) return false;
        int i = 0, j = 0;
        while (j < curr.length()) {
            if (i < prev.length() && prev.charAt(i) == curr.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == prev.length();
    }


}
