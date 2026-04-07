class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int targetIdx = n-k;

        return quickSelect(nums, 0 , n-1,targetIdx);
    }

    public int quickSelect(int nums[] , int left , int right , int targetIdx){
        if(left == right) return nums[left];
        
        int randomIdx = left + (int)((right - left + 1)*Math.random());
        swap(nums,randomIdx,right);

        int j = left;
        for(int i = left ; i < right ;i++){
            if(nums[i] < nums[right]){
                swap(nums,i,j);
                j++;
            }
        }

        swap(nums,right,j);

        if(j == targetIdx)
        return nums[j];

        else if(j < targetIdx){
            return quickSelect(nums,j+1,right,targetIdx);
        }

        else
            return quickSelect(nums,left,j-1,targetIdx);
    }

    public void swap(int nums[] , int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}