package genericcollections;

public class MyLinkedList<E extends Comparable<E>> {

    Node<E> head, tail;

    int size;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    public void addFirst(E data) {
        Node<E> temp = new Node<>(data);
        if (head == null) {
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;
        }
        size++;
    }

    public void addLast(E data) {
        Node<E> temp = new Node<>(data);

        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        size++;

    }

    public String toString() {

        Node<E> cur = head;
        String ret = "";
        while (cur != null) {
            ret += cur.data + " ";
            cur = cur.next;
        }
        return ret;
    }

    public MyLinkedList<Integer> insertSorted(MyLinkedList<Integer> list, Integer number) {

        MyLinkedList<Integer> ret = new MyLinkedList<>();
        //      Node<Integer> temp = ret.head;

        Node<Integer> cur = list.head;
        while (cur != null) {
            if (cur.data.compareTo(number) < 0) {
//                Node<Integer> temp = new Node<>(cur.data);
//
//                if (ret.tail == null) {
//                    ret.head = ret.tail = temp;
//                } else {
//                    ret.tail.next = temp;
//                    ret.tail = temp;
//                }
//                ret.size++;

            } else {
//                Node<Integer> temp = new Node<>(number);
//
//                if (ret.tail == null) {
//                    ret.head = ret.tail = temp;
//                } else {
//                    ret.tail.next = temp;
//                    ret.tail = temp;
//                }
//                ret.size++;
                break;
            }
            cur = cur.next;
        }

        if (cur == null) {
            Node<Integer> temp = new Node<>(number);

            if (ret.tail == null) {
                ret.head = ret.tail = temp;
            } else {
                ret.tail.next = temp;
                ret.tail = temp;
            }
            ret.size++;
        }

        while (cur != null) {
            Node<Integer> temp = new Node<>(cur.data);

            if (ret.tail == null) {
                ret.head = ret.tail = temp;
            } else {
                ret.tail.next = temp;
                ret.tail = temp;
            }
            ret.size++;
            cur = cur.next;
        }
        return ret;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    E unlink(Node<E> x) {
        final E element = x.data;
        final Node<E> next = x.next;
        final Node<E> prev = x.pre;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.pre = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.pre = prev;
            x.next = null;
        }
        x.data = null;
        size--;
        return element;
    }

    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator
            implements java.util.Iterator<E> {

        private Node<E> current = head; // Current index

        public boolean hasNext() {
            return (current != null);
        }

        public E next() {
            E e = current.data;
            current = current.next;
            return e;
        }

        public void remove() {
            System.out.println("Implementation left as an exercise");
        }
    }

    // this fun -- to find intersection of two sorted linked lists
    public Node<E> fun(Node<E> a, Node<E> b) {

        if (a == null || b == null) {
            return null;
        }

        if (a.data.compareTo(b.data) < 0) {
            return fun(a.next, b);

        }

        if (a.data.compareTo(b.data) > 0) {
            return fun(a, b.next);
        }

        Node<E> ret = new Node<>(a.data);
        ret.next = fun(a.next, b.next);
        return ret;

    }
}
