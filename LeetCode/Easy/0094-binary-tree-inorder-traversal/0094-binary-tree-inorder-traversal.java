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
    List<Integer> ans ;
    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        //inOrderRecursive(root);
        inOrderStack(root);
        return ans;
    }
    
    void inOrderRecursive(TreeNode root){
        if(root == null) return;
        inOrderRecursive(root.left);
        ans.add(root.val);
        inOrderRecursive(root.right);
    }

    void inOrderStack(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;

    // FIX 1: Use OR (||). Loop runs if we have a node OR a stack.
    while (curr != null || !stack.isEmpty()) {
        
        // FIX 2: Push the CURRENT node first, then move left.
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        // Pop the leftmost available node
        curr = stack.pop();
        ans.add(curr.val);

        // FIX 3: Simply move to the right child. 
        // The outer loop will handle pushing it if it exists.
        curr = curr.right;
    }
}
}