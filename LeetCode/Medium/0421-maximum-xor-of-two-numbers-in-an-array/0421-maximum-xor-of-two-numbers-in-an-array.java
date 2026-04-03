class Solution {
    public class Trie{
        public class Node{
            Node links[] = new Node[2];
        }

        Node root ;

        Trie(){
            root = new Node();
        }

        public void insert(int number){
            Node curr = root;

            for(int i = 31 ;i >= 0 ; i-- ){
                //check if bit is on or not
                int bit = (number >> i) & 1;

                //make new node accordingly
                if(curr.links[bit] == null)
                curr.links[bit] = new Node();

                curr = curr.links[bit];
                
            }
        }

        public int getMax(int number){
            Node curr = root;
            int xor = 0;
            for(int i = 31 ;i >= 0 ; i-- ){
                //get ith bit
                int bit = (number >> i) & 1;

                //find opposite bit and turn on in our answer
                if(curr.links[1-bit] != null){
                    curr = curr.links[1-bit];
                    xor |= (1<<i);   
                }
                

                else{
                     curr = curr.links[bit];
                }

            }

            return xor;
        }
    }
    public int findMaximumXOR(int[] nums) {
        int n = nums.length ;
        Trie trie = new Trie();

        //insert everything
        for(int number: nums){
            trie.insert(number);
        }

        int ans = Integer.MIN_VALUE;

        //check each number for max 
        for(int num: nums){
            int potential = trie.getMax(num);
            ans = Math.max(potential,ans);
        }

        return ans;
        
    }
}