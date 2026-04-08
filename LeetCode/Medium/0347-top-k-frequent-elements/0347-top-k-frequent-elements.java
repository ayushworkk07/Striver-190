class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        return bucketSolution(nums,k);
    }

    public int[] bucketSolution(int nums[] ,int k){

        HashMap<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        
        //make freq map
        for(int i = 0 ;i < n ; i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        //each ith index reperesent a frequency list
        ArrayList<Integer> freqList [] = new ArrayList[n+1];

        
        for(int key : map.keySet()){
            if(freqList[map.get(key)] == null)
            freqList[map.get(key)] = new ArrayList<>();

            freqList[map.get(key)].add(key);
        }

        int ans[] = new int[k];

        //reverse iterate on freqList and add ans till k length
        int j = 0;
            for(int i = freqList.length - 1 ; i>=0 ; i--){
                if(freqList[i] != null){
                     for(int val : freqList[i]){
                    if(j == k)
                        return ans;

                        ans[j++] = val;
                    }
                }
            }

        return ans;
    }

}