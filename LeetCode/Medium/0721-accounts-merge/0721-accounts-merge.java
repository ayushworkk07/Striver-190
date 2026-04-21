class Solution {
    /* An edge exist for email -> index in the HM<email,index> , if at any moment we get an email
    which is already in the map , we union map.get(email) and i
    cause it is duplicate
    -> we traverse the map and add the emails in the parent(index)
    */
   public static class DSU{
        int parent[];
        int size[];

        //1 based indexing
        DSU(int n){
            this.parent = new int[n];
            this.size = new int[n];

            Arrays.fill(size,1);

            for(int i = 0 ;i < n ; i++){
                parent[i] = i;
            }
        }

        public boolean union(int u , int v){
            int pu = findParent(u);
            int pv = findParent(v);

            if(pu == pv) 
            return false;

            if(size[pu] > size[pv]){
                size[pu] += size[pv];
                parent[pv] = pu;
            }
            else{
                size[pv] += size[pu];
                parent[pu] = pv;
            }

            return true;
        }

        public int findParent(int node){
            if(parent[node] == node)
                return node;

            return parent[node] = findParent(parent[node]);
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU graph = new DSU(n);
        HashMap<String,Integer> map = new HashMap<>();

        for(int i =0 ;i < n ; i++){
            for(int j = 1 ;j < accounts.get(i).size() ; j++){
                String email = accounts.get(i).get(j);
                
                if(map.containsKey(email)){
                    graph.union(map.get(email),i);
                }
                else
                    map.put(email,i);
            }
        }

        ArrayList<ArrayList<String>> temp = new ArrayList<>();
        for(int i =0 ;i < n ; i++)
        temp.add(new ArrayList<>());

        for(String key : map.keySet()){
            String email = key;
            int index = graph.findParent(map.get(key));

            temp.get(index).add(email);
        }

        for(int i =0 ;i < n ; i++)
            Collections.sort(temp.get(i));

        List<List<String>> ans = new ArrayList<>();

        for(int i =0 ;i < temp.size() ; i++){
            if(temp.get(i).size() > 0){
                String firstName = accounts.get(i).get(0);
                ArrayList<String> list = temp.get(i);

                list.add(0,firstName);

                ans.add(new ArrayList<>(list));
            }
        }

        return ans;
    }
}