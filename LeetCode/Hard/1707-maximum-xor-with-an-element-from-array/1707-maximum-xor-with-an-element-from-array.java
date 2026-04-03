class Solution {
    public class Trie{
        public class Node{
            Node links[] = new Node[2];
        }

        Node root ;

        Trie(){
            root = new Node();
        }

        void insert(int number){
            Node curr = root;

            for(int i = 30 ; i >= 0 ; i--){
                int bit = (number >> i) & 1;

                if(curr.links[bit]== null)
                    curr.links[bit] = new Node();

                curr = curr.links[bit];
            }
        }

        int getMax(int number){
            Node curr = root;
            int xor = 0;
            for(int i = 30 ; i >= 0 ; i--){
                int bit = (number >> i) & 1;

                if(curr.links[1-bit]!=null){
                    xor |= (1<<i);
                    curr = curr.links[1-bit];
                }

                else{
                    curr = curr.links[bit];
                }
            }

            return xor;
        }
    }
    public int[] maximizeXor(int[] nums, int[][] queries) {
        //sort nums
        Arrays.sort(nums);

        int m = queries.length , n = queries[0].length;
        int[][] offlineQueries = new int[m][3];
        
        int idx = 0;
        for(int i = 0 ;i < m ; i++){
            offlineQueries[i][0] = queries[i][0];
            offlineQueries[i][1] = queries[i][1];
            offlineQueries[i][2] = idx++;
        }

        // comparator to sort offline queries based on mi and store idx
        Arrays.sort(offlineQueries,(a,b)-> Integer.compare(a[1],b[1]));

        int[] ans = new int[m];
        Trie trie = new Trie();

        //keep adding nums in trie till they are < mi and getMax
        int j = 0;
        for(int[] query : offlineQueries){
            int number = query[0] , max = query[1] ;
            idx = query[2];
            
            while(j < nums.length && nums[j] <= max){
                trie.insert(nums[j++]);
            }

            //if the trie is empty || the first query max < nums[0]
            if(j == 0){
                ans[idx] = -1;
            }
            else
            ans[idx] = trie.getMax(number);
        }

        return ans;
    }
}



/*  1) we treat them like offline queries
            2) sort the queries according to mi, storing index also
            3) sorting nums
            4)inserting nums[i] till they are < mi and working on those queires
        */