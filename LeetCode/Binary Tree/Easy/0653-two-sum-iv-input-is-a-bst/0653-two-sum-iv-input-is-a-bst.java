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
    //can be done through finding inorder array and using 2 pointer
    public class BSTIterator{
        Stack<TreeNode> stack;
        boolean forward;                   //sorted from front direction
        BSTIterator(TreeNode root,boolean forward){
            stack = new Stack<>();
            this.forward = forward;
            pushAll(root,forward);
        }
        public void pushAll(TreeNode root , boolean forward){
            if(root == null) return;

            TreeNode curr = root;

            while(curr!=null){
                stack.push(curr);
                
                curr= forward == true ? curr.left : curr.right;
            }
        }

        public int next(){
            TreeNode curr = stack.pop();

            if(forward == true) pushAll(curr.right,true);
            else pushAll(curr.left,false);
                
            return curr.val;
        }
        
    }
    public boolean findTarget(TreeNode root, int k) {
        BSTIterator left = new BSTIterator(root , true);
        BSTIterator right = new BSTIterator(root , false);

        int front = left.next() , back = right .next();
        while(front < back){
            int sum = front + back;
            if(sum == k) return true;

            else if(sum < k) front = left.next();
            else back = right.next();
        }

        return false;
    }
}