class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length ; 

        int low = 0 , high = n-1;
        while(low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target)
                return mid;

            // Step 1: Identify which half is sorted
            if (nums[low] <= nums[mid]) { 
                // Left half is sorted
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // Target is in the sorted left half
                } else {
                    low = mid + 1;  // Target is in the right half
                }
            } else { 
                // Right half is sorted
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;  // Target is in the sorted right half
                } else {
                    high = mid - 1; // Target is in the left half
                }
            }
        }

        return -1;
    }
}