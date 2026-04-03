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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // postorderRecursion(root ,ans);
        // return ans;
        return postorderIterative1(root);
    }

    public void postorderRecursion(TreeNode root , List<Integer> ans){
        
        if(root == null) return;
        postorderRecursion(root.left,ans);
        postorderRecursion(root.right,ans);
        ans.add(root.val);
    }

    //reverse pre order
    public List<Integer> postorderIterative1(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        TreeNode curr = root;
        
        while(curr!= null || !stack.isEmpty()){
            //push all right

            while(curr!=null){
                //we add in reverse to make it post order else it becomes actualyy reverse pre order from the right
                ans.addFirst(curr.val);       
                stack.push(curr);

                curr = curr.right;
            }

            //pop
            curr = stack.pop();
            
            //move left
            curr = curr.left;
        }

        return ans;
    }
}