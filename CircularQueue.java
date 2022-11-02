/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import Interfaces_.List_;

/**
 *
 * @author USER
 */
public class CircularQueue<E> {

    E[] elem;
    int f = -1;
    int r = -1;
    int size = 0;

    public CircularQueue(int k) {

        elem = (E[]) new Object[k];

    }

    public boolean add(E val) { // addLast // enqueue
        if (isFull()) {
            return false;
        }

        if (f == -1) {
            f = 0;
        }
        r = (r + 1) % elem.length;
        elem[r] = val;
        size++;
        return true;
    }

    public boolean remove() {
        if (isEmpty()) {
            return false;
        }

        E ret = elem[f];

        if (f == r) {
            f = r = -1;
        } else {
            f = (f + 1) % elem.length;
        }
        size--;
        return true;
    }

    public boolean isFull() {

        return f == ((r + 1) % elem.length);

    }

    public boolean isEmpty() {
        return f == -1;
    }

    public E front() {
        if (isEmpty()) {
            return null;
        }
        return elem[f];
    }

    public E rear() {
        if (isEmpty()) {
            return null;
        }
        return elem[r];
    }

}
