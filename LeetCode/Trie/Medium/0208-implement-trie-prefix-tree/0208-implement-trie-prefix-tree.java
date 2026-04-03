class Trie {
    public class Node{
        Node links[] = new Node[26];
        boolean isEnd = false;
    }

    Node root ;
    public Trie() {
        root = new Node();
    }

    
    public void insert(String word) {
        Node curr = root;

        for(int i = 0 ;i < word.length() ; i++){
            char ch = word.charAt(i);
            int pos = ch - 'a';

            if(curr.links[pos]==null){
                curr.links[pos] = new Node();
            }
            curr = curr.links[pos];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        Node curr = traverse(word);

        return curr == null ? false : curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = traverse(prefix);

        return curr == null ? false : true;
    }

    public Node traverse(String str){
        Node curr = root;

        for(int i = 0;i<str.length() ; i++){
             char ch = str.charAt(i);
             int pos = ch - 'a';
             if(curr.links[pos]==null){
                return null;
            }
            curr = curr.links[pos];
        }

        return curr;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */