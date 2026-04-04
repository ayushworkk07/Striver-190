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
    //can do the same with keeping a global idx instead of arr[0]
    //also can find inorder and solve it like when pre and in is given
    public TreeNode build(int[] preorder , int[] arr , int upperbound){
        int idx = arr[0];
        if(arr[0] >= preorder.length || preorder[idx] > upperbound )
            return null;

        
        TreeNode root = new TreeNode(preorder[idx],null,null);
        arr[0]++;
        
        root.left = build(preorder,arr,root.val);
        root.right = build(preorder,arr,upperbound);

        return root;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        int arr[] = new int[1];

        return build(preorder,arr , Integer.MAX_VALUE);
    }
}