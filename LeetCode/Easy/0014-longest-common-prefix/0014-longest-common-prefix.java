class Solution {
    public class Trie{
        public class Node {
            Node[] links = new Node[26];
            int countPrefix = 0;
        }
        Node root;

        Trie(){
            this.root = new Node();
        }

        public void insert(String word){
            Node curr = root;

            for(char ch : word.toCharArray()){
                int idx = ch - 'a';

                if(curr.links[idx] == null){
                    curr.links[idx] = new Node();
                }
                curr = curr.links[idx];
                curr.countPrefix++;
            }
        }

        //longest common prefix will exist in all words so countPrefix = totalwords then append
        public String getLongestPrefix(String word , int totalWords){
            Node curr = root;
            StringBuilder sb = new StringBuilder();
            
            for(int i = 0 ;i < word.length() ; i++){    
                char ch = word.charAt(i);
                int idx = ch - 'a';

                if(curr.links[idx] == null) break;

                curr = curr.links[idx];

                if(curr.countPrefix == totalWords)
                    sb.append(ch);
                
            }
            return sb.toString();
        }
    }
    
    public String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();

        for(String word : strs){
            trie.insert(word);
        }

        return trie.getLongestPrefix(strs[0],strs.length);

    //   return solution2(strs);   
    }

    //for each character in word1 check if it exists across all words break when found the mismatch
   public String solution2(String[] words){
        if(words.length == 1) 
            return words[0];
        
        String first = words[0];
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ;i < first.length() ; i++){
            char current = first.charAt(i);
            
            for(int j = 1 ; j< words.length ; j++){
                String second = words[j];

                if(second.length() < i || second.charAt(i)!= current)
                    return sb.toString();
            }
            sb.append(current);
        }

        return sb.toString();
    }
}