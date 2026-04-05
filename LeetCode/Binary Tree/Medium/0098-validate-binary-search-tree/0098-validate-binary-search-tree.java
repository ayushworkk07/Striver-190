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
    //pass upperbound and lowerbound for each right and left subtree
    public boolean isValid(TreeNode root , long min , long max){
        if(root == null) return true;

        if(root.val >= max || root.val <= min)
            return false;

        boolean left = isValid(root.left , min , root.val);
        boolean right = isValid(root.right,root.val , max);
        

        return (left && right);
    }
    public boolean isValidBST(TreeNode root) {
        
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
}