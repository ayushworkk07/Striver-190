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
    1) store inorder in map for quick rootIndex
    2) pre[0] becomes treeRoot and calls for left and right subtree
*/
class Solution {
    public TreeNode build(int inOrderStart,int inOrderEnd,int[]preorder){
            if(inOrderStart > inOrderEnd)
                return null;

            int val = preorder[rootIndexPreorder++];
            int inOrderRootIndex = map.get(val);

            TreeNode root = new TreeNode(val,null,null);
            
            root.left = build(inOrderStart,inOrderRootIndex-1,preorder);
            root.right = build(inOrderRootIndex+1,inOrderEnd,preorder);

            return root;
    }

    int rootIndexPreorder;
    HashMap<Integer,Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        rootIndexPreorder=0;
        map = new HashMap<>();

        //store inorder in map for quick root lookup
        for(int i = 0 ;i < inorder.length ; i++)
            map.put(inorder[i],i);


        return build(0,inorder.length-1,preorder);
    }
}