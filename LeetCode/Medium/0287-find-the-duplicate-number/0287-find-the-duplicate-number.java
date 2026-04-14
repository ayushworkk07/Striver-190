class Solution {
    public int findDuplicate(int[] nums) {
        // Phase 1: Detect that a cycle exists
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];           // slow moves 1 step
            fast = nums[nums[fast]];     // fast moves 2 steps
        } while (slow != fast);

        // Phase 2: Find the entrance to the cycle (the duplicate)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];           // both move 1 step
            fast = nums[fast];
        }

        return slow;
    }
}
