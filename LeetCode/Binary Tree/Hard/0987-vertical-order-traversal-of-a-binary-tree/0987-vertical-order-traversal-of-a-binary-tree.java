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
        TreeNode node; int row; int col;
        Pair(TreeNode node , int row,int col){
            this.node = node; this.row = row; this.col = col;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //BFS on map<col,map<row,pq>>
        TreeMap<Integer,TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(root,0,0));

        while(q.size()!=0){
            int size = q.size();

            for(int i = 0 ;i <size ; i++){
                Pair pair = q.remove();
                int row = pair.row , col = pair.col;
                TreeNode node = pair.node;

                map.computeIfAbsent(col, k -> new TreeMap<>())
                .computeIfAbsent(row , k -> new PriorityQueue<>())
                .add(node.val);

                if(node.left!=null) q.add(new Pair(node.left,row+1,col-1));
                if(node.right!=null) q.add(new Pair(node.right,row+1,col+1));
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        

        for(TreeMap<Integer, PriorityQueue<Integer>> x : map.values() ){
            ArrayList<Integer> lis = new ArrayList<>();
            for(PriorityQueue<Integer> pq : x.values()){
                while(!pq.isEmpty()){
                    lis.add(pq.remove());
                }
            }
            ans.add(lis);
        }
        return ans;
    }
}