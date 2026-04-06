class Solution {
    public String countAndSay(int n) {
        if(n == 1)return "1";

        StringBuilder res = new StringBuilder();
        res.append("1");

        //loop from 2 to n
        for(int i = 2 ;i <= n ; i++){
            res = helper(res);
        }

        return res.toString();
    }

    public StringBuilder helper(StringBuilder res){

        int i = 0;
        StringBuilder ans = new StringBuilder();

        //keep counting same characters and then append them like 3a2b
        while(i < res.length()){
            char ch = res.charAt(i);
            int count = 0;

            while(i < res.length() && res.charAt(i) == ch){
                count++;
                i++;
            }
                

            ans.append(count).append(ch);
        }

        return ans;
    }
}