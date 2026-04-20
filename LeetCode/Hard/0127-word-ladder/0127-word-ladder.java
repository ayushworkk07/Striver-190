class Solution {
    /* 1) add all words in the HS , which will act as O(1) visited
    
    2) apply BFS on startWord by trying all 26 letters on every index 
    if any newWord matches in the HS , add in Q and remove from visited

    3)if a newWord == endWord return count
    */
    public class Pair{
        String word;
        int count ;

        Pair(String word , int count){
            this.word = word ;
            this.count = count;
        }
    }
     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for(String s: wordList)
            set.add(s);

        ArrayDeque<Pair>q = new ArrayDeque<>();
        q.add(new Pair(beginWord,1));


        while(!q.isEmpty()){
            Pair node = q.poll();
            String currWord = node.word;
            int count = node.count;

            if(currWord.equals(endWord))
            return count;

             for (int i = 0; i < currWord.length(); i++) {
                char[] chars = currWord.toCharArray();

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String newWord = new String(chars);

                    if (set.contains(newWord)) {
                        q.add(new Pair(newWord,count+1));
                        set.remove(newWord);
                    }
                }
            }
        }


        return 0;
    }
}