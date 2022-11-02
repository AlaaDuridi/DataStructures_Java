/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import Interfaces_.Collection_;
import Interfaces_.List_;
import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList_<E> extends MyAbstractList<E> {

    private static final int INITIAL_CAPACITY = 16;

    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    public MyArrayList_() {
    }

    public MyArrayList_(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    public boolean add(int index, E e) {
        ensureCapacity();

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection_<? extends E> c) {

        E[] s = (E[]) new Object[c.size()];
        c.toArray(s);
        E[] temp = (E[]) new Object[size + c.size()];
        int k = 0;
        for (int i = 0; i < index; i++) {
            temp[k++] = data[i];
        }
        for (int i = 0; i < c.size(); i++) {
            temp[k++] = s[i];
        }

        for (int i = index; i < size; i++) {
            temp[k++] = data[i];
        }
        data = temp;
        return true;
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size) // Make a new array of a's runtime type, but my contents:
        {
            return (T[]) Arrays.copyOf(data, size);
        }
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return ret;
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object e) {
        for (int i = size - 1; i >= 0; i--) {
            if (e.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public List_<E> subList(int from, int to) {
        List_<E> ret = new MyArrayList_();
        for (int i = from; i < to + 1; i++) {
            ret.add(data[i]);
        }
        return ret;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] temp = (E[]) new Object[size * 2 + 1];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
    }

    @Override
    public boolean containsAll(Collection_<?> c) {
        E[] s = (E[]) new Object[c.size()];
        c.toArray(s);
        for (int i = 0; i < s.length; i++) {
            if (!contains(s[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection_<?> c) {
        E[] s = (E[]) new Object[c.size()];
        c.toArray(s);

        boolean flag = false;
        for (int i = 0; i < s.length; i++) {
            flag = remove(s[i]);

        }
        return flag;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean addAll(Collection_<? extends E> c) {
        addAll(size, c);
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }

        return result.toString() + "]";
    }

    public void trimToSize() {
        if (size != data.length) {
            E[] newData = (E[]) (new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        } // If size == capacity, no need to trim
    }

    private class ArrayListIterator
            implements java.util.Iterator<E> {

        private int current = 0; // Current index

        @Override
        public boolean hasNext() {
            return (current < size);
        }

        @Override
        public E next() {
            return data[current++];
        }

        @Override
        public void remove() {
            MyArrayList_.this.remove(current);
        }
    }

}
