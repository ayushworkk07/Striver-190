class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        for(int i = nums2.length-1 ; i>=0 ; i--){
            int num = nums2[i];
            while(!stack.isEmpty() && num >= stack.peek())
                stack.pop();

            map.put(num , !stack.isEmpty() ? stack.peek() : -1);

            stack.push(num);
        }

        int ans[] = new int[nums1.length];

        for(int i = 0 ;i < nums1.length ; i++){
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}