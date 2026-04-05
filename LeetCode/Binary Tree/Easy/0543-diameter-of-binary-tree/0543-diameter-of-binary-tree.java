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
    public int diameter(TreeNode root , int[]ans){
        if(root == null)return 0;

        int leftDiameter= diameter(root.left,ans);
        int rightDiameter = diameter(root.right,ans);

        //check if between left and right is the biggest diameter
        ans[0] = Math.max(ans[0],leftDiameter+rightDiameter);
        
        //return 1+ max of lST or RST
        return Math.max(leftDiameter,rightDiameter)+1;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        int ans[] = new int[1];    //can be passed by reference alternate to global variable
        diameter(root,ans);
        return ans[0];  
    }
}