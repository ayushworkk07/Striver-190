/* this happens because of java tax which you can learn about from ai 
    as Java runs on JVM and pauses code for garbage collector 
    to do its job it take more time
*/

class Solution {
    /* 1) call BFS like in wordLadder 1 , and keep adding distance of all words in a HM<word,distance>
    2) backtrack from beginWord to endWord using the HM<word,distance> by distance + 1 for each valid word
    3) add to path and return answer in the end
    */
    public class Pair{
        String word;
        int count ;

        Pair(String word , int count){
            this.word = word ;
            this.count = count;
        }
    }
    HashMap<String,Integer> map;
    String bWord;
    List<List<String>> ans;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        map = new HashMap<>();
        bWord = bWord;
        ans = new ArrayList<>();

        
        HashSet<String> set = new HashSet<>();
        for(String s: wordList)
            set.add(s);

        ArrayDeque<Pair>q = new ArrayDeque<>();
        q.add(new Pair(beginWord,1));
        map.put(beginWord,1);


         while(!q.isEmpty()){
            int size = q.size();
            List<String> usedOnLevel = new ArrayList<>(); // Track words used at this level
            boolean found = false;

            for(int k = 0 ;k < size ; k++){
                Pair node = q.poll();
                String currWord = node.word;
                int count = node.count;

                if(currWord.equals(endWord) || found == true) break;

                char[] chars = currWord.toCharArray();
                
                //use chars instead of stringbuilder to avoid TLE and MLE
                for (int i = 0; i < currWord.length(); i++) {
                    char original = chars[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String newWord = new String(chars);

                        if (set.contains(newWord)) {
                            q.add(new Pair(newWord,count+1));
                            //only add to map if its the first time
                            if(map.containsKey(newWord) == false)
                            map.put(newWord,count+1);

                            usedOnLevel.add(newWord);
                        }
                    }
                    chars[i] = original;
                }
            }
            //only remove from set when that level is done
            for(String s : usedOnLevel)
            set.remove(s);
        }

        
        //call dfs to build path backwards
        if(map.containsKey(endWord)){
            ArrayList<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord,path);
        }
        return ans;
    }

    public void dfs(String currentWord,ArrayList<String> path){
        if(currentWord.equals(bWord)){
            ans.add(new ArrayList<>(path));
            return;
        }

        String currWord = currentWord;
        int count = map.get(currWord);  //steps
        
        char[] chars = currWord.toCharArray();

        for (int i = 0; i < currWord.length(); i++) {
            char original = chars[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String prevWord = new String(chars);

                // backtrack
                if(map.containsKey(prevWord) && map.get(prevWord) == count-1){
                    path.add(prevWord);
                    dfs(prevWord,path);
                    path.remove(path.size()-1);
                }
            }
            chars[i] = original;

        }

    }
}