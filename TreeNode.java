/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

public class TreeNode<E extends Comparable<E>> {

    public E data;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E data) {
        this.data = data;
    }

//    TreeNode(E data, TreeNode left, TreeNode right) {
//        this.data = data;
//        this.left = left;
//        this.right = right;
//    }
    public TreeNode() {
    }
}
