/*
class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    public boolean isLeaf(Node root){
        if(root.left == null && root.right == null)return true;
        return false;
    }
    

    public void addLeft(Node root, ArrayList<Integer> ans){
        Node curr = root.left;
        while(curr!=null){
            if(!isLeaf(curr)) ans.add(curr.data);
            if(curr.left!=null) curr = curr.left;
            else  curr = curr.right;
        }
    }
    public void addLeaf(Node root, ArrayList<Integer> ans){
        if(root == null) return ;
        
        addLeaf(root.left,ans);
        
        if(isLeaf(root))
        ans.add(root.data);
        
        addLeaf(root.right,ans);
    }

    public void addRight(Node root, ArrayList<Integer> ans){
        Node curr = root.right;  
        ArrayList<Integer> list = new ArrayList<>()  ; //we can only have root once in the tree
        while(curr!=null){
            if(!isLeaf(curr)) list.addFirst(curr.data);
            if(curr.right!=null) curr = curr.right;
            else  curr = curr.left;
        }
        for(int number : list)
            ans.add(number);
    }
    ArrayList<Integer> boundaryTraversal(Node root) {
        if(root == null)return new ArrayList<Integer>();
        ArrayList<Integer> ans = new ArrayList<>();
        if(!isLeaf(root))ans.add(root.data);
        addLeft(root,ans);
        addLeaf(root,ans);
        addRight(root,ans);
        return ans;
        
    }
}