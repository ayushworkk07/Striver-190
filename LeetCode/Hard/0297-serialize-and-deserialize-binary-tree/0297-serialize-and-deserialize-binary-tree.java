/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 /*
    1) add in a string while serializing
 */
public class Codec {

    public String serialize(TreeNode root) {
        if (root == null) return "";

        //we cannot add null nodes in ArrayDequeu so we take linkedlist
        Queue<TreeNode> q = new LinkedList<>(); 
        q.add(root);

        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr == null) {
                sb.append("n ");
                continue;
            }

            sb.append(curr.val).append(" ");
            q.add(curr.left);
            q.add(curr.right);
        }

        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;

        String[] split = data.trim().split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        int i = 1;

        while (!q.isEmpty() && i < split.length) {
            TreeNode curr = q.poll();

            // left
            if (!split[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(split[i]));
                curr.left = left;
                q.add(left);
            }
            i++;

            // right
            if (i < split.length && !split[i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(split[i]));
                curr.right = right;
                q.add(right);
            }
            i++;
        }

        return root;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));