class Solution {
    /* f(i,j,isTrue) give you the maximum number of ways to make expression i till j into isTrue
    for each left partition and right partition we need the no.of ways to make them true and False
    so we can correctly find the number of ways for different operator like ^, &, |
    */
    static int countWays(String s) {
        int n = s.length();
        return f(s,0,n-1,true , new Integer[n+1][n+1][2]);
    }


    static int f(String s , int start , int end , boolean isTrue, Integer[][][] dp){
        if(start == end){
            int val = 0;
            if(isTrue){
                val = (s.charAt(start) == 'T' ? 1 : 0);
                return dp[start][end][isTrue ? 1: 0] = val;
            }

            else{
                val = (s.charAt(start) == 'F' ? 1 : 0);
                return dp[start][end][isTrue ? 1: 0] = val;
            }
        }

        if(dp[start][end][isTrue ? 1: 0]!=null)
            return dp[start][end][isTrue ? 1: 0];

        int count = 0;
        for(int k = start ; k <= end ; k++){
            if(isOperator(s.charAt(k))){
                char operator = s.charAt(k);

                int count_true_L = f(s,start,k-1,true,dp);
                int count_false_L = f(s,start,k-1,false,dp);

                int count_true_R = f(s,k+1,end,true,dp);
                int count_false_R = f(s,k+1,end,false,dp);

                if(operator == '|'){
                    if(isTrue)
                        count += (count_true_L * count_true_R) +
                    (count_true_L * count_false_R) + (count_false_L * count_true_R);

                    else
                        count += count_false_L * count_false_R;

                }
                else if(operator == '&'){
                    if(isTrue)
                        count += (count_true_L * count_true_R) ;

                    else
                        count += (count_false_L * count_false_R) +
                    (count_true_L * count_false_R) + (count_false_L * count_true_R);
                }
                else if((operator == '^')){
                    if(isTrue)
                        count += (count_true_L * count_false_R) + (count_false_L * count_true_R);

                    else
                        count += (count_false_L * count_false_R) + (count_true_L * count_true_R) ;
                }
            }
        }

        return dp[start][end][isTrue ? 1: 0]= count;
    }

    static boolean isOperator(char ch){
        if(ch == '|' || ch == '^' || ch == '&')
            return true;

        return false;
    }
}