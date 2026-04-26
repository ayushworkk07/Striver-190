class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for(String s : wordList)
            set.add(s);

        Queue<String> q = new ArrayDeque<>();
        q.add(beginWord);

        int level = 1;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i =0 ;i < size ; i++){
                String word  = q.poll();
                if(word.equals(endWord))
                    return level;
                
                char[] wordArray = word.toCharArray();

                for(int j = 0 ;j < wordArray.length ; j++){
                    char orignal = wordArray[j];
                    for(char ch = 'a' ; ch <= 'z' ; ch++){
                        wordArray[j] = ch;
                        String newWord = new String(wordArray);

                        if(set.contains(newWord)){
                            set.remove(newWord);
                            q.add(newWord);
                        }
                    }
                    wordArray[j] = orignal; 
                }
            }
            level++;
        }

        return 0;
    }
}