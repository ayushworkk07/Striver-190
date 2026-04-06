class Solution {
    /*
        1) can solve by splitting("\\.") and integer.parseInt O(N+M)
        2) can solve by 2 pointers and making values            O(Max(N,M)) - we traverse only one string at max
    */
     public int compareVersion(String version1, String version2) {
        int n = version1.length() , m = version2.length();
        int i = 0 , j = 0 ;

        while(i < n || j < m){

            int num1 = 0 , num2 = 0;

            while(i < n && version1.charAt(i) != '.'){
                num1 = num1*10 + version1.charAt(i) - '0';
                i++;
            }

            while(j < m && version2.charAt(j) != '.'){
                num2 = num2*10 + version2.charAt(j) - '0';
                j++;
            }

            if(num1 < num2 ) return -1;
            else if(num1 > num2) return 1;

            i++;
            j++;
        }

        return 0;
    }

}