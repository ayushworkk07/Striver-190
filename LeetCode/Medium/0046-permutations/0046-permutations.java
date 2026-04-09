class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean used[] = new boolean[nums.length];
        backTrack(nums , 0, new ArrayList<>(),ans,used);
        return ans;
    }

    public void backTrack(int[]nums , int index, ArrayList<Integer> currList , List<List<Integer>> ans , boolean used[]){
        if(currList.size() == nums.length){
            ans.add(new ArrayList<>(currList));
            return;
        }

        //mark true current element and call for others
        for(int i =0 ; i < nums.length ; i++){
            if(used[i]) continue;

            used[i] = true;
            currList.add(nums[i]);
            backTrack(nums,index,currList,ans,used);
            currList.remove(currList.size()-1);
            used[i] = false;
        }

    }
}