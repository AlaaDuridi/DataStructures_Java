package genericcollections;

import Interfaces_.Deque;
import java.util.NoSuchElementException;

// implementing deque
public class DequeDoublyLinkedList<E extends Comparable<Node>> implements Deque<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void addFirst(E e) {
        Node<E> temp = new Node<E>(e);
        if (head == null) {
            head = tail = temp;
        } else {
            head.setPre(temp);
            temp.setNext(head);
            head = temp;
        }
        size++;
    }

    @Override
    public void addLast(E e) {

        Node<E> temp = new Node<E>(e);
        if (tail == null) {
            head = tail = temp;
        } else {
            temp.setPre(tail);
            tail.setNext(temp);
            tail = temp;
        }
        size++;
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public boolean removeFirstOccurence(Object o) {
        if (head == null) {
            return false;
        }
        Node<E> cur = head;
        for (int i = 0; i < size && cur != null; i++, cur = cur.getNext()) {
            if (cur.getData().equals(o)) {
                if (cur == head) {
                    cur.getNext().setPre(cur.getPre());
                    head = cur.getNext();

                } else if (cur == tail) {
                    cur.getPre().setNext(cur.getNext());
                    tail = cur.getPre();

                } else {
                    cur.getNext().setPre(cur.getPre());
                    cur.getPre().setNext(cur.getNext());

                }
                size--;
                return true;

            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurence(Object o) {
        if (head == null) {
            return false;
        }

        Node<E> cur = tail;
        for (int i = 0; i < size && cur != null; i++, cur = cur.getPre()) {
            if (cur.getData().equals(o)) {
                if (cur == head) {
                    cur.getNext().setPre(cur.getPre());
                    head = cur.getNext();

                } else if (cur == tail) {
                    cur.getPre().setNext(cur.getNext());
                    tail = cur.getPre();

                } else {
                    cur.getNext().setPre(cur.getPre());
                    cur.getPre().setNext(cur.getNext());

                }
                size--;
                return true;

            }

        }
        return false;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E peekFirst() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    @Override
    public E peekLast() {
        if (tail == null) {
            return null;
        }
        return tail.getData();
    }

    @Override
    public E pollFirst() {

        if (head == null) {
            return null;
        }

        E ret = head.getData();
        if (size == 1) {
            head = tail = null;
        } else {
            head.getNext().setPre(null);
            head = head.getNext();
        }
        size--;
        return ret;
    }

    @Override
    public E pollLast() {
        if (tail == null) {
            return null;
        }
        E ret = tail.getData();
        if (size == 1) {
            tail = head = null;
        } else {
            tail.getPre().setNext(null);
            tail = tail.getPre();
        }

        size--;
        return ret;

    }

    @Override
    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            return peekFirst();
        }
    }

    @Override
    public E getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        } else {
            return peekLast();
        }
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            return pollFirst();
        }
    }

    @Override
    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        } else {
            return pollLast();
        }
    }

    @Override
    public E pop() {
        return removeFirst();
    }

}
