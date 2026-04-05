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

    public TreeNode build(int inOrderStart, int inOrderEnd , int[]postorder){
        if(inOrderStart > inOrderEnd)
            return null;

        int val = postorder[rootIndexPostOrder--];
        int inOrderRootIndex = map.get(val);

        TreeNode root = new TreeNode(val , null , null);

        root.right = build(inOrderRootIndex+1,inOrderEnd,postorder);
        root.left = build(inOrderStart,inOrderRootIndex-1,postorder);

        return root;
    }


    HashMap<Integer,Integer> map ;
    int rootIndexPostOrder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        int n = postorder.length;
        rootIndexPostOrder = n-1;
        map = new HashMap<>();

        for(int i = 0 ;i < postorder.length;i++)
            map.put(inorder[i],i);


        return build(0,n-1,postorder);
    }
}