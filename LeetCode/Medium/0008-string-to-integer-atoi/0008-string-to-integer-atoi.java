class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;

        while(i < n && s.charAt(i) == ' ')
            i++;

        if(i == n) return 0;

        int sign = 1;

        if(s.charAt(i) == '-' || s.charAt(i)=='+'){
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        while(i < n && s.charAt(i) == '0')
            i++;

        int total = 0;

        while(i < n && Character.isDigit(s.charAt(i))){

            int num = s.charAt(i) - '0';

            //check overflow
            if(total > Integer.MAX_VALUE/10 || 
            (total == Integer.MAX_VALUE/10 && num > Integer.MAX_VALUE%10 ) ){
                total = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                return total;
            }

            total = total*10 + num ;
            i++;
        }

        return total*sign;
    }
}