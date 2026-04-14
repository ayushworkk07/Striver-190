class Solution {
    public void nextPermutation(int[] nums) {

        int n = nums.length;
        int i = n-2;
        
        // find first dip
        while(i >=0 && nums[i]>= nums[i+1])
        i--;
    
        
        //swap with first right greatest
        if(i >= 0){
            int j = n-1;
            while(nums[j] <= nums[i])
            j--;

            swap(nums,i,j);

        }
        
        //reverse from next of pivot
        reverse(nums,i+1,n-1);

    }

    public void swap(int nums[],int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int nums[] ,int start, int end){
        int i = start , j= end;

        while(i < j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
}