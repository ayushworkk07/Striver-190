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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // preorderRecursion(root,ans);
        //    preOrderStack(root,ans);
        // return ans;
        
        return preorderMorrison(root);

        
    }
    public void preorderRecursion(TreeNode root , List<Integer> ans){
        if(root == null)
        return;

        ans.add(root.val);

        preorderRecursion(root.left,ans);
        preorderRecursion(root.right,ans);
    }

    public void preOrderStack(TreeNode root, List<Integer> ans){
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while(curr!=null || !stack.isEmpty()){
            //add node ,move left
            while(curr!=null){
                ans.add(curr.val);
                stack.push(curr);

                curr = curr.left;
            }

            //pop node move right
            curr = stack.pop();
            curr = curr.right;
        }
    }

    public List<Integer> preorderMorrison(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        
        //same logic as inorder just while cutting the thread we add node
        while(curr != null){

            if(curr.left == null){
                ans.add(curr.val);
                curr = curr.right;
            }

            else{
                TreeNode prev = curr.left;

                while(prev.right != null && prev.right != curr){
                    prev = prev.right;
                }

                if(prev.right == null){
                    prev.right = curr;
                    ans.add(curr.val);
                    curr = curr.left;
                }
                else{
                    prev.right = null;

                    curr = curr.right;
                }
            }
        }
        return ans;
    }
}
