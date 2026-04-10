class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        pickNotPick(candidates,0,0,target,new ArrayList<Integer>(),ans);
        return ans;
    }

    public void pickNotPick(int[] candidates, int index , int sum , int target ,ArrayList<Integer>currList,List<List<Integer>> ans){
        if(sum > target)
            return;

        if(index == candidates.length){
            if(sum == target){
                ans.add(new ArrayList<>(currList));
            }
            return;
        }
        
        currList.add(candidates[index]);
        
        //try this index again
        pickNotPick(candidates,index,sum+candidates[index],target,currList,ans);
        
        currList.remove(currList.size()-1);

        pickNotPick(candidates,index+1,sum,target,currList,ans);
    }
}