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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null)return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while(q.size()!=0){
           
            int size = q.size() ;
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0 ;i < size;i++){
                TreeNode curr = q.remove();
                TreeNode left = curr.left,right=curr.right;
                int value = curr.val  ;

                
                list.add(value);

                if(left!=null)q.add(left);
                if(right!=null)q.add(right);
            }
            ans.add(list)   ;
        }

        return ans;
    }
}