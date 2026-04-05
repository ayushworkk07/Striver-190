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

    public void helper(TreeNode root){
        if(root == null)
            return;

        helper(root.right);
        helper(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
    TreeNode prev;
    public void flatten(TreeNode root) {
        prev = null;
        helper(root);
    }
}