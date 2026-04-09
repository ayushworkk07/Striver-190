class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        pickNotPick(nums ,0, new ArrayList<>() , ans);

        return ans;
    }

    public void pickNotPick(int[] nums , int index , ArrayList<Integer> currentList , List<List<Integer>> ans){
        if(index == nums.length){
            //we modify the same reference of currentList in the recursion so we need to store a copy of it
            ans.add(new ArrayList<>(currentList));       
            return;
        }

        //pick first occurance
        currentList.add(nums[index]);
        pickNotPick(nums,index+1,currentList,ans);
        
        currentList.remove(currentList.size()-1);

        //skip duplicatess
        while(index < nums.length-1 && nums[index] == nums[index+1])
        index++;

        //not pick call and move to next unique
        pickNotPick(nums , index+1,currentList,ans);
    }
}