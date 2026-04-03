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
    List<Integer> inorderTraversal(TreeNode root){
    //    return inorderIterative(root);
         return inorderMorrison(root);
    }

    List<Integer> inorderIterative(TreeNode root){
         List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curr = root;
        
        while(!stack.isEmpty() || curr != null){
            //keep pushing node and moving to left
            while(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            ans.add(curr.val);

            curr = curr.right;
        }

        return ans;
    }

    List<Integer> inorderMorrison(TreeNode root){

        TreeNode curr = root;
        List<Integer> ans = new ArrayList<>();

        while(curr!=null ){

            //here we are using the thread to get back to the parent
            if(curr.left == null){
                ans.add(curr.val);
                curr = curr.right;
            }

            else{
                //move left and then get rightmost child
                TreeNode prev = curr.left;

                while(prev.right!=null && prev.right!=curr){
                    prev = prev.right;
                }

                if(prev.right == null){
                    //make thread and move left
                    prev.right = curr;
                    curr = curr.left;
                }

                else{
                    //if thread exists already processed
                    ans.add(curr.val);
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;
    }
}