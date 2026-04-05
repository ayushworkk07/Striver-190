class Solution {
    public String longestPalindrome(String s) {
        
        int start = 0 , end = 0;

        for(int i = 0 ; i < s.length() ; i++){

            int len1 = checkPalindrom(s,i , i);
            int len2 = checkPalindrom(s,i,i+1);

            int len = Math.max(len1,len2);
            
            //finding start and end this way works for both odd and even center palindrome
            if(end-start+1 <= len){
                start = i - (len-1)/2;
                end = i + len/2;
            }
        }

        return s.substring(start,end+1);
    }

    int checkPalindrom(String word,int i , int j){

        while(i >= 0 && j < word.length() && word.charAt(i) == word.charAt(j)){
            i--;
            j++;
        }
        return (j+1) - (i+1) -1;       //both are i and j ends at unequal characterrs
    }
}