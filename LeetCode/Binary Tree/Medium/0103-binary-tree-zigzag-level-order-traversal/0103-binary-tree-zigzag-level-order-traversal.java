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
    //BFS with a variable to define if we want straight or reverse order traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int reverse = 0;
        while(q.size()>0){

            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>();

            for(int i = 0; i< size ; i++ ){
                
                TreeNode node = q.remove();
                
                if(reverse==0)list.addLast(node.val);
                if(reverse==1)list.addFirst(node.val);

                if(node.left!=null)q.add(node.left);
                if(node.right!=null)q.add(node.right);
        
            }
            ans.add(list);
            reverse = 1 - (reverse & 1);   //BIT on logic
        }

        return ans;
    }
}