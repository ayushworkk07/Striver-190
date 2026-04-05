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
    public class Pair{
        TreeNode node; int idx;
        Pair(TreeNode node , int idx){
            this.node = node;
            this.idx = idx;
        }
    }
    //BFS and find diff of first and last node 
    //to avoid overflow decrement the minimum index for each level from each node
    //use 2*(i-min)+1 and 2*(i+min)+2 to find indexes of next left and right node
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(root,0));
        int max = Integer.MIN_VALUE;

        while(q.size()!=0){
            int size = q.size();

            int min = q.peek().idx;
            int low = 0 , high = 0;
            for(int i =0 ; i < size ; i++){

                Pair pair = q.remove();
                TreeNode node = pair.node; int idx = pair.idx - min;
                if(i == 0) low = idx;
                if(i == size-1) high = idx;

                if(node.left!= null) q.add(new Pair(node.left,2*idx+1));
                if(node.right!= null) q.add(new Pair(node.right,2*idx+2));

            }
            max = Math.max(max,high-low+1);
        }

        return max;

    }
}