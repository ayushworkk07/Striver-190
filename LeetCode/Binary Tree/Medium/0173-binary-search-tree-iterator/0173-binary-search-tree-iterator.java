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
class BSTIterator {
//BSTiterator is a controled inorder traversal where you can stop after inserting each left spine and dont have to travel the whole of tree
    Stack<TreeNode> stack ;
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        pushAll(root);
    }

    public void pushAll(TreeNode root){
        if(root == null) return;

        TreeNode curr = root;

        while(curr != null){
            stack.push(curr);
            curr = curr.left;
        }
    }
    
    public int next() {
        TreeNode curr = stack.pop();

        pushAll(curr.right);

        return curr.val;
    }
    
    public boolean hasNext() {
        return stack.size() > 0;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */