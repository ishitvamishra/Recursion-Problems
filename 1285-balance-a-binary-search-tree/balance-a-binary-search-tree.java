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
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(list, root); // bst ka inorder sorted array deta toh pehle bst se array banao phir usse balanced bst bana lo
        return buildBST(list, 0, list.size()-1);
    }

    public void inorder(List<Integer> list, TreeNode root){
        if(root == null) return;

        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);
    }

    public TreeNode buildBST(List<Integer> list, int start, int end){
        if(start > end) return null;
        //start se mid -1 tak left  me hoga aur mid+1 se end tak right me aur mid node ban jayega 
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(list.get(mid));

        node.left = buildBST(list, start, mid-1);
        node.right = buildBST(list, mid+1, end);

        return node;
    }
}