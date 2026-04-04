/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //for sorted array = find middle and let it build leftsubtree and right subtree
    public TreeNode build(int[] nums , int left , int right){
        if(left > right)return null;
        int middle = left + (right-left)/2;

        TreeNode root = new TreeNode(nums[middle],null,null);

        root.left = build(nums,left,middle-1);
        root.right = build(nums,middle+1,right);

        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        return build(nums,0,n-1);
    }
}