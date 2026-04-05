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

/*
    1) call validateBST (O(n)) for all nodes then that subtree sum = O(n^2)

    2)define a new class with max , min , isBST and sum 
    2.1)and handle everything in postOrder
*/
class Solution {
    public int ans;
    public int maxSumBST(TreeNode root) {
        if(root == null) return 0;

        ans = 0;
        solve(root);

        return ans;
    }

    public class NodeInfo{
        int max , min , sum;
        boolean isBST;

        NodeInfo(int max , int min , int sum , boolean isBST){
            this.max = max ; this.min = min ; this.sum = sum;
            this.isBST = isBST;
        }
    }

    public NodeInfo solve(TreeNode root){
        if(root == null)
            return new NodeInfo(Integer.MIN_VALUE,Integer.MAX_VALUE,0,true);

        NodeInfo left = solve(root.left);
        NodeInfo right = solve(root.right);
    

        if(left.isBST && right.isBST && root.val > left.max && root.val < right.min){
            int currentSum = left.sum + right.sum + root.val;
            ans = Math.max(ans,currentSum);
            
            //while sending max and min it should the be most max and least values in the subtree
            return new NodeInfo(
                Math.max(right.max,root.val),
                Math.min(left.min,root.val),
                currentSum,
                true
            );
        }
        
        return new NodeInfo(Integer.MAX_VALUE,Integer.MIN_VALUE,0,false);
        
    }
}