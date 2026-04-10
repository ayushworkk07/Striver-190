class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        
        List<List<Integer>> ans = new ArrayList<>();

        pickNotPick(candidates,0,0,target,new ArrayList<>(),ans);

        return ans;
    }

    public void pickNotPick(int nums[] , int index ,int sum, int target , ArrayList<Integer> currList , List<List<Integer>> ans){
        if(sum > target)return;         //optimized
        
        if(index == nums.length){
           if(target == sum)
            ans.add(new ArrayList<>(currList));
            
            return;
        }

        currList.add(nums[index]);

        //pick
        pickNotPick(nums , index+1 ,sum+nums[index], target , currList , ans);

        currList.remove(currList.size()-1);
        
        //skip duplicate elements
        while(index < nums.length-1 && nums[index] == nums[index+1])
            index++;

        //not pick
        pickNotPick(nums,index+1,sum,target,currList,ans);
    }
}