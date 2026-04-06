class Solution {
    public int strStr(String haystack, String needle) {
        return kmpalgorithm(haystack,needle);
    }

    public int kmpalgorithm(String text , String pattern){

        //find lps array
        int[] lps = getLpsArray(pattern);

        int i = 0 , j = 0;
        int n = text.length()  , m = pattern.length();

        ArrayList<Integer> patternIndexes = new ArrayList<>();

        while(i < n){
            //if chars equal increment
            while(i < n && j < m && text.charAt(i) == pattern.charAt(j)){
                i++;j++;
            }

            //if pattern is found , move j back to last matched prefix and keep comparing
            if(j == m){
                patternIndexes.add(i-j);
                j = lps[j-1];
            }

            //if chars are not equal keep moving j back to prevous matched prefix , if j becomes 0 iterate i and move to next character in text
            else if(i < n && text.charAt(i) != pattern.charAt(j)){
                if(j!=0) j = lps[j-1];
                else i++;
            }
        }
        return patternIndexes.size() > 0 ? patternIndexes.get(0) : -1;
    }

    //longest prefix suffix array
    public int[] getLpsArray(String pattern){
        int n = pattern.length();
        int[] lps = new int[n];

        int i = 1;
        int len = 0;

        //compare prefix and suffix
        while(i < n){
            
            //if equal store in LPS 
            if(pattern.charAt(len) == pattern.charAt(i)){
                len++;
                lps[i] = len;
                i++;
            }

            else{

                if(len == 0){
                    lps[i] = 0;
                    i++;
                }
                //move j to just previous prefix matched and compare again
                else{
                    len = lps[len-1];
                }
            }
        }

        return lps;
    }
    
}