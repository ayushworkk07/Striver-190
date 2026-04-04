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
    public int height(TreeNode root , int[] ans){
        if(root == null) return 0;
        
        //if we are getting -ve values from left or right we dont consider that path as max and move forward with 0 values for left and right
        int leftMax = Math.max(0,height(root.left,ans));
        int rightMax = Math.max(0,height(root.right,ans));
        ans[0] = Math.max(ans[0],leftMax+rightMax+root.val);
        
        return Math.max(leftMax,rightMax)+root.val;
    }
    public int maxPathSum(TreeNode root) {
        int[] ans = new int[1];
        ans[0] = Integer.MIN_VALUE;
        height(root,ans);

        return ans[0];
    }
}