/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import java.util.Objects;

/**
 *
 * @author USER
 * @param <E>
 *
 */
public class BST<E extends Comparable<E>> {

    public TreeNode root;
    int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean search(E e) {
        TreeNode<E> cur = root;
        while (cur != null) {

            if (cur.data.equals(e)) {
                return true;
            }

            if (e.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    public boolean insert(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
            size++;
            return true;
        } else {
            TreeNode<E> cur = root;
            while (true) {

                if (e.compareTo(cur.data) > 0) {
                    if (cur.right == null) {
                        cur.right = new TreeNode(e);
                        size++;
                        return true;
                    } else {
                        cur = cur.right;
                    }
                } else if (e.compareTo(cur.data) < 0) {
                    if (cur.left == null) {
                        cur.left = new TreeNode(e);
                        size++;
                        return true;
                    } else {
                        cur = cur.left;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public TreeNode<E> searchBST(TreeNode<E> root, E val) {
        if (root == null || Objects.equals(root.data, val)) {
            return root;
        } else if (val.compareTo(root.data) > 0) {
            return searchBST(root.right, val);

        } else {
            return searchBST(root.left, val);
        }

    }

    public boolean delete(E val) {

        TreeNode<E> ret = delete(root, val);
        return ret != null;
    }

    public TreeNode<E> delete(TreeNode<E> root, E val) {

        if (root == null) {
            return null;
        }
        if (val.compareTo(root.data) < 0) {
            root.left = delete(root.left, val);
        } else if (val.compareTo(root.data) > 0) {
            root.right = delete(root.right, val);
        } else {

            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            E min = minValInRightSubTree(root.right).data;
            root.right = delete(root.right, min);
            root.data = min;
        }
        return root;
    }

    private TreeNode<E> minValInRightSubTree(TreeNode<E> node) {

        if (node.left == null) {
            return node;

        }
        return minValInRightSubTree(node.left);
    }

    public static void main(String[] args) {
        BST<Integer> t = new BST<>();
        int a[] = {8, 6, 9, 5, 7, 15, 13};
        for (var i : a) {
            t.insert(i);
        }

        t.inOrder();
        System.out.println("");

        t.delete(6);
        t.inOrder();
        System.out.println("");

    }
}
