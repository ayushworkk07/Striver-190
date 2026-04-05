class Solution {
    public int repeatedStringMatch(String a, String b) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        //for b to be a substring of 'a', 'a' has to be atleast till length b
        while(sb.length() < b.length()){
            sb.append(a);
            count++;
        }
        //b might start at the end of a , so we need to add one more to repeat enough a's such that any pattern starting from 0 or middle is repeated
        if(rabinKarpStringMatching(sb.toString(),b)) return count;
        
        else{
            sb.append(a);
            count++;

            if(rabinKarpStringMatching(sb.toString(),b))
            return count;
        }

        return -1;
    }

    public boolean rabinKarpStringMatching(String text, String pattern) {

        long base = 31 , mod =(long) 1e9 + 7;

        long hash = 1 , p = 0 , t = 0;

        int n = text.length() , m = pattern.length();

        //hash calculation
        for(int i = 0 ;i < m-1; i++)
            hash = (hash*base) % mod;

        //initial p and t hashes

        for(int i = 0 ;i < m ; i++){
            p = (p*base + pattern.charAt(i))%mod;
            t = (t*base + text.charAt(i))%mod;
        }


        for(int i = 0 ; i <= n-m ; i++){
            
            if(p == t){
                //check if there is no collision
                if(text.substring(i,i+m).equals(pattern))
                    return true;
            }
                

            else if(i < n-m){

                //roll the hash
                t = (base* (t - text.charAt(i)*hash) + text.charAt(i+m))% mod;

                if(t < 0)
                    t += mod;
            }

        }

        return false;
    }

}