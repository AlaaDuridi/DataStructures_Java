package genericcollections;

import java.util.Arrays;
import java.util.Collections;

public class MyArrayList<E> {

    public MyArrayList() {
    }

    public MyArrayList(E[] a) {

        for (int i = 0; i < a.length; ++i) {
            this.add(a[i]);
        }
    }
    E[] a = (E[]) new Object[2];
    int size = 0;

    public int size() {
        return size;
    }

    public MyArrayList<E> merge(MyArrayList<E> x, MyArrayList<E> y) {
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }

        MyArrayList<E> ret = new MyArrayList<>();

        int i = 0, j = 0;
        System.out.println(x.size);
        System.out.println(y.size);
        while (i < x.size && j < y.size) {

            if (x.get(i).hashCode() < (y.get(j)).hashCode()) {
                ret.add(x.get(i));
                i++;
            } else {
                ret.add(y.get(j));
                j++;
            }

        }

        while (i < x.size) {
            ret.add(x.get(i));
            i++;
        }
        while (j < y.size) {
            ret.add(y.get(j));
            j++;
        }
        return ret;

    }

    public boolean add(E val) {

        ensureCap();

        a[size] = val;
        size++;
        return true;
    }

    public E get(int id) {

        if (id < 0 || id >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return a[id];
    }

    private void ensureCap() {
        if (size == a.length) {

            E temp[] = (E[]) new Object[(int) (a.length * 2)];

            System.arraycopy(a, 0, temp, 0, a.length);

            a = temp;
        }
    }

    public void addFirst(E val) {

        ensureCap();

        for (int i = size; i > 0; i--) {
            a[i] = a[i - 1];
        }
        a[0] = val;
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("invalid index");
        }
        E ret = a[index];

        for (int i = index; i < size - 1; i++) {
            a[i] = a[i + 1];
        }
        size--;

        return ret;
    }

    public String toString() {

        if (size == 0) {
            return "[]";
        }

        String ret = "";

        for (int i = 0; i < size; i++) {

            ret += a[i] + " ";

        }
        return "[ " + ret + "]";
    }

    public int indexOf(Object o) {

        int ret = -1;

        for (int i = 0; i < size; i++) {
            if (a[i].equals(o)) {
                ret = i;
                return ret;

            }
        }
        return ret;
    }

    public E removeFirst() {

        if (size == 0) {
            throw new IndexOutOfBoundsException("invalid");
        }

        E ret = a[0];

        for (int i = 0; i < size - 1; i++) {
            a[i] = a[i + 1];
        }
        size--;
        return ret;
    }

    public E removeLast() {
        if (size > 0) {
            E ret = a[size - 1];
            size--;
            return ret;
        } else {
            return null;
        }
    }

    public boolean add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("invalid index");
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            add(data);
        } else {
            ensureCap();

            for (int i = size; i > index; i--) {
                a[i] = a[i - 1];
            }
            a[index] = data;
            size++;

        }
        return true;
    }

    public void clear() {
        size = 0;
    }

    public boolean remove(Object o) {
        final Object[] es = a;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++) {
                    if (es[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (o.equals(es[i])) {
                        break found;
                    }
                }
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    private void fastRemove(Object[] es, int i) {

        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    public E set(int index, E val) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("invalid index");
        }

        E ret = a[index];
        a[index] = val;
        return ret;
    }

    public static <T> MyArrayList<T> intersect(MyArrayList<T> A, MyArrayList<T> B) {

        MyArrayList<T> ret = new MyArrayList<>();

        int a = 0, b = 0;

        while (a < A.size() && b < B.size()) {

            if (a == 0 || !A.get(a).equals(A.get(a - 1))) {

                if (A.get(a).equals(B.get(b))) {
                    ret.add(A.get(a));
                    a++;
                    b++;
                } else if (A.get(a).hashCode() > B.get(b).hashCode()) {
                    b++;
                } else {
                    a++;
                }
            }

        }
        return ret;
    }

    // two sorted
    public static MyArrayList<Integer> intersect2(MyArrayList<Integer> a, MyArrayList<Integer> b) {
        MyArrayList<Integer> s = new MyArrayList();

        for (int i = 0; i < a.size; i++) {
            if (i == 0 || a.get(i) != a.get(i - 1)) {

                int l = 0, r = b.size - 1, mid, ans = -1;

                while (l <= r) {
                    mid = (l + r) / 2;

                    if (b.get(mid) == a.get(i)) {

                        ans = i;
                        break;
                    } else if (b.get(mid) < a.get(i)) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                if (ans != -1) {
                    s.add(a.get(i));
                }
            }
        }
        return s;

    }

    public int lastIndexOf(Object val) {

        int ret = -1;

        for (int i = size - 1; i >= 0; i--) {
            if (a[i].equals(val)) {
                ret = i;
                return ret;
            }
        }
        return ret;
    }

    public boolean contains(Object val) {
        for (int i = 0; i < size; i++) {
            if (a[i].equals(val)) {
                return true;
            }
        }
        return false;
    }

    public void trimToSize() {

        if (a.length != size) {

            E t[] = (E[]) new Object[size];

            for (int i = 0; i < size; i++) {
                t[i] = a[i];
            }

            a = t;
        }
    }

    public boolean removeByVal(Object o) {

        for (int i = 0; i < size; i++) {
            if (a[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int removeAllThisValue(Object o) {
        int ret = 0;

        for (int i = 0; i < size; i++) {

            if (a[i].equals(o)) {
                ret++;
                //removeByIndex
                E t[] = (E[]) new Object[size - 1];
                int k = 0;
                for (int j = 0; j < i; j++) {
                    t[k++] = a[j];
                }

                for (int j = i + 1; j < size; j++) {
                    t[k++] = a[j];
                }
                size--;

                a = t;
                i--;
            }
        }

        return ret;
    }

    public boolean retainAll(Object[] arr) {
// O(n^2)
        int common = 0, k = 0;

        for (int i = 0; i < size; i++) {
            boolean found = false;
            for (int j = 0; j < arr.length; j++) {

                if (a[i].equals(arr[j])) {

                    common++;
                    found = true;
                    break;
                }
            }

            if (found == false) {

                remove(i);
                i--;
            }
        }

        int needtodelete = size - common;

        if (needtodelete == 0) {
            return false;
        }

        return true;

    }

    public static void main(String[] args) {
        Integer x[] = {1, 2, 8, 9};
        Integer y[] = {3, 4, 10};
        MyArrayList<Integer> xx = new MyArrayList<>(x);
        MyArrayList<Integer> yy = new MyArrayList<>(y);

        MyArrayList<Integer> ans = xx.merge(xx, yy);
        System.out.println(ans);

    }
}
