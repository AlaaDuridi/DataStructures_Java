/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import Interfaces_.Collection_;
import Interfaces_.List_;
import Interfaces_.Queue_;
import java.util.EmptyStackException;
import java.util.Iterator;

public class MyQueue<E> implements Queue_<E> {

    private MyArrayList<E> elements;
    private int start;
    private int size;

    public MyQueue() {
        elements = new MyArrayList();
        start = 0;
        size = 0;

    }

    @Override
    public boolean add(E data) {
        elements.add(data);
        size++;
        return true;
    }

    @Override
    public boolean offer(E data) {
        elements.add(data);
        size++;
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E ret = elements.get(start);
        size--;
        start++;
        return ret;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E ret = elements.get(start);
        size--;
        start++;
        return ret;

    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(start);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements.get(start);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection_<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection_<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection_<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
