/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;
        if(p.val < root.val && q.val < root.val)    //both are on the left
            return lowestCommonAncestor(root.left , p ,q);
        
        if(p.val > root.val && q.val > root.val)    //both are on the right
            return lowestCommonAncestor(root.right , p ,q);

        //this is the LCA
        return root;
    }
}