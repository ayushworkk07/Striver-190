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
    public List<Integer> rightSideView(TreeNode root) {
        //reversePreorder and at each level we take the first guy from the right
        List<Integer> ans = new ArrayList<>();
        // recursive(root , ans , 0);
        // return ans;
        
        //BFS is enough dont go for iterative and morrison for this
        return bfs(root);
        
        
    }

    //reverse preorder
    public void recursive(TreeNode root , List<Integer> ans , int level){
        if(root == null)
        return ;

        if(ans.size() == level && root != null){
            ans.add(root.val);
        }
            

        recursive(root.right , ans , level+1);
        recursive(root.left,ans,level+1);
    }

    public List<Integer> bfs(TreeNode root){
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();

        q.add(root);

        while(q.size()!=0){

            int size = q.size();

            for(int i =0; i < size ; i++){
                TreeNode node = q.remove();

                if(i == size-1)
                    ans.add(node.val);

                if(node.left!=null)q.add(node.left);
                if(node.right!=null)q.add(node.right);
            }
        }
        return ans;

    }

}

    