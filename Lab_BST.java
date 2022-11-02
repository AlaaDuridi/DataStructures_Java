package genericcollections;

public class Lab_BST<E extends Comparable<E>> {

    TreeNode<E> root;
    int size = 0;

    public boolean insert(E e) {
        if (root == null) {
            root = new TreeNode(e);
            size++;
            return true;
        } else {
            TreeNode<E> cur = root;
            while (true) {
                if (e.compareTo(cur.data) > 0) {
                    if (cur.right == null) {
                        size++;
                        cur.right = new TreeNode(e);
                        return true;
                    } else {
                        cur = cur.right;
                    }
                } else if (e.compareTo(cur.data) < 0) {
                    if (cur.left == null) {
                        size++;
                        cur.left = new TreeNode(e);
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

    public boolean search(E e) {

        if (root == null) {
            return false;
        }

        TreeNode<E> cur = root;
        while (cur != null) {
            if (e.compareTo(cur.data) > 0) {
                cur = cur.right;
            } else if (e.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                return true;
            }
        }
        return false;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode<E> root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode<E> root) {
        if (root != null) {
            System.out.println(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(TreeNode<E> root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data + " ");
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    private boolean isLeaf(TreeNode<E> node) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        return false;
    }

    public int countOfNodes() {
        return countOfNodes(root);
    }

    private int countOfNodes(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        return countOfNodes(root.right) + countOfNodes(root.left);
    }

    private boolean isParent(TreeNode<E> node) {
        if (node == null) {
            return false;
        }
        return node.left != null || node.right != null;
    }

    public void printLeaf() {
        printLeaf(root);
    }

    private void printLeaf(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            System.out.println(root.data + " ");
        }
        printLeaf(root.left);
        printLeaf(root.right);
    }

    public E minElement() {
        return minElement(root);
    }

    private E minElement(TreeNode<E> root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.data;
        }
        return minElement(root.left);
    }

    public E maxElement() {
        if (root == null) {
            return null;
        }
        return maxElement(root);
    }

    private E maxElement(TreeNode<E> root) {

        if (root.right == null) {
            return root.data;
        }
        return maxElement(root.right);
    }

    public void levelOrder(int level) {
        int h = height();
        for (int i = level; i <= h; i++) {
            levelOrder(i, root);
            System.out.println("");
        }

    }

    private void levelOrder(int level, TreeNode<E> root) {
        if (root == null) {
            return;
        }
        if (level == 0) {
            System.out.print(root.data + " ");
        } else if (level > 0) {
            levelOrder(level - 1, root.left);
            levelOrder(level - 1, root.right);
        }
    }
}
