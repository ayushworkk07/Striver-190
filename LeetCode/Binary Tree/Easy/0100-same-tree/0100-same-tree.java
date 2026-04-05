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
    //traverse simultaneously
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //both are null
        if(p == null && q == null) return true;

        //one is null or values are not same
        if(p==null || q==null || p.val != q.val) return false;

            if(isSameTree(p.left,q.left) == false || 
            isSameTree(p.right,q.right) == false)
            return false;

        return true;
    }
}