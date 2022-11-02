package genericcollections;

import java.util.ArrayList;

public class AVL<E extends Comparable<E>> {

    private AVLNode<E> root;
    private int size = 0;

    class AVLNode<E extends Comparable<E>> {

        int height = 0;

        E data;
        AVLNode<E> left, right;

        public AVLNode(E e) {
            this.data = e;
        }
    }

    public boolean insert(E e) {
        if (root == null) {
            root = new AVLNode<>(e);
            size++;
            return true;
        } else {
            AVLNode<E> cur = root;
            while (true) {

                if (e.compareTo(cur.data) > 0) {
                    if (cur.right == null) {
                        cur.right = new AVLNode(e);
                        size++;
                        return true;
                    } else {
                        cur = cur.right;
                    }
                } else if (e.compareTo(cur.data) < 0) {
                    if (cur.left == null) {
                        cur.left = new AVLNode(e);
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

    public boolean insertAVL(E e) {

        if (insert(e)) {
            balancePath(e);
            return true;
        }
        return false;
    }

    private ArrayList<AVLNode<E>> path(E e) {

        ArrayList<AVLNode<E>> list = new ArrayList<>();

        AVLNode<E> cur = root;
        while (cur != null) {
            list.add(cur);
            if (e.compareTo(cur.data) > 0) {
                cur = cur.right;
            } else if (e.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                break;
            }

        }
        return list;
    }

    public void balancePath(E e) {

        ArrayList< AVLNode<E>> p = path(e);

        for (int i = p.size() - 1; i >= 0; i--) {
            AVLNode<E> a = p.get(i);
            updateHeight(a);
            // parent of a

            AVLNode<E> parent;
            if (i == 0) {
                parent = null;
            } else {
                parent = p.get(i - 1);
            }

            switch (bf(a)) {
                case -2:
                    if (bf(a.right) <= 0) {
                        LL(a, parent);

                    } else {
                        //  l for it , rigth for child
                        LR(a, parent);
                    }
                    break;
                case +2:
                    if (bf(a.left) >= 0) {
                        // rigth rotation
                        RR(a, parent);

                    } else {
                        // rigt for it , left for child
                        RL(a, parent);
                    }
            }
        }

        // switch bf
    }

    private void updateHeight(AVLNode<E> node) {
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left == null) {
            node.height = 1 + (node.right).height;
        } else if (node.right == null) {
            node.height = 1 + ((node.left)).height;
        } else {
            node.height = 1 + Math.max(((node.right)).height, ((node.left)).height);
        }
    }

    private int bf(AVLNode<E> node) {

        // left - right
        if (node.right == null) {
            return node.height;
        } else if (node.left == null) {
            return 0 - node.height;
        } else {
            return ((node.left)).height - ((node.right)).height;
        }
    }

    private void LL(AVLNode<E> a, AVLNode<E> parent) {
        //rigth heavy
        AVLNode<E> b = a.right;

        if (a == root) {
            root = b;
        } else {

            if (parent.left == a) {
                parent.left = b;
            } else {
                parent.right = b;
            }
        }
        a.right = b.left;
        b.left = a;
        updateHeight(a);
        updateHeight(b);

    }

    private void RR(AVLNode<E> a, AVLNode<E> parent) {

        // left heavy
        AVLNode<E> b = a.left;
        if (a == root) {
            root = b;

        } else {

            if (parent.left == a) {
                parent.left = b;
            } else {
                parent.right = b;
            }
        }

        a.left = b.right;
        b.right = a;

        updateHeight(a);
        updateHeight(b);
    }

    private void LR(AVLNode<E> a, AVLNode<E> parent) {

        // a is right heavy -- left
        // b is left heavy == rigth ;
        AVLNode<E> b = a.right;

        AVLNode<E> c = b.left;
        if (a == root) {
            root = c;
        } else if (parent.left == a) {
            parent.left = c;
        } else {
            parent.right = c;
        }

        a.right = c.left;
        b.left = c.right;
        c.right = b;
        c.left = a;

        updateHeight(a);
        updateHeight(b);
        updateHeight(c);
    }

    private void RL(AVLNode<E> a, AVLNode<E> parent) {
        AVLNode<E> b = a.left;
        AVLNode<E> c = b.right;
        if (a == root) {
            root = c;
        } else if (parent.left == a) {
            parent.left = c;
        } else {
            parent.right = c;
        }

        a.left = c.right;
        b.right = c.left;
        c.left = b;
        c.right = a;

        updateHeight(a);
        updateHeight(b);
        updateHeight(c);
    }

    public boolean delete(E e) {
        if (root == null) {
            return false;
        }

        AVLNode<E> parent = null, cur = root;

        while (cur != null) {
            if (e.compareTo(cur.data) < 0) {
                parent = cur;
                cur = cur.left;
            } else if (e.compareTo(cur.data) > 0) {
                parent = cur;
                cur = cur.right;
            } else {
                break; //cur is e
            }
        }
        if (cur == null) {
            return false;
        }

        if (cur.left == null) {  // no left children
            if (root == cur) {
                root = cur.right;
            } else {

                if (e.compareTo(parent.data) < 0) { // isleftchild
                    parent.left = cur.right;
                } else {
                    parent.right = cur.right;
                }
                balancePath(parent.data);
            }
        } else {

            AVLNode<E> parentOfRightMost = cur;
            AVLNode<E> rightMost = cur.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;

            }
            //replace cur(e) with rigth most in left sutree

            cur.data = rightMost.data;

            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
            balancePath(parentOfRightMost.data);

        }
        size--;
        return true;
    }

}
