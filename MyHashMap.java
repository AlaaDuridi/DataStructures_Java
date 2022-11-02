package genericcollections;

import Interfaces_.MyMap;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<k, v> implements MyMap<k, v> {

//    private int trimToPowerOfTwo(int initialCapacity) {
//
//      int capacity =1 ;
//      while(capacity < initialCapacity)
//      {
//          capacity<<=1 ; //*=2
//      }
//      return capacity ;
//    }
    // inner class for hash node
    class HashNode<k, v> {

        MyMap.Entry<k, v> entry;
        HashNode next;
        HashNode pre;

        HashNode(k key, v value) {
            entry = new MyMap.Entry<>(key, value);
        }

        public v getValue() {
            return entry.getValue();
        }

        public k getKey() {
            return entry.getKey();
        }

        public void setValue(v value) {
            entry.setValue(value);
        }

        public void setKey(k key) {
            entry.setKey(key);
        }
    } // end of inner class
    // capacity is prefered to be power of two
    private static final int INITIAL_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30; // same as 2^30
    private int capacity;
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    private float loadFactor;
    private int size;
    HashNode<k, v>[] table;

    public MyHashMap() {
        this(INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = initialCapacity;
        }
        this.loadFactor = loadFactor;
        table = new HashNode[capacity];
    }

    /**
     *
     */
    @Override
    public void clear() {
        size = 0;
        this.capacity = INITIAL_CAPACITY;
        table = new HashNode[capacity];

    }

    @Override
    public boolean containsKey(k key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(v value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {

                HashNode cur = table[i];
                while (cur != null) {
                    if (cur.getValue().equals(value)) {
                        return true;
                    }
                    cur = cur.next;
                }
            }
        }
        return false;
    }

    @Override
    public Set<MyMap.Entry<k, v>> entrySet() {
        Set<MyMap.Entry<k, v>> ret = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                HashNode<k, v> cur = table[i];
                while (cur != null) {
                    ret.add(cur.entry);
                    cur = cur.next;
                }
            }
        }
        return ret;
    }

    @Override
    public v get(k key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            HashNode<k, v> cur = table[bucketIndex];
            for (; cur != null; cur = cur.next) {
                if (cur.getKey().equals(key)) {
                    return cur.getValue();
                }
            }
        }
        return null;
    }

    private int hash(int hashCode) {
        return hashCode & (capacity - 1);// if capacity is not power of two , use % capacity
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<k> keySet() {
        Set<k> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                HashNode<k, v> cur = table[i];
                for (; cur != null; cur = cur.next) {
                    set.add(cur.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public v put(k key, v value) {
        if (get(key) != null) {
            int bucketIndex = hash(key.hashCode());
            HashNode<k, v> cur = table[bucketIndex];
            for (; cur != null; cur = cur.next) {
                if (cur.getKey().equals(key)) {
                    v oldValue = cur.getValue();
                    cur.setValue(value);
                    return oldValue;
                }
            }
        }
        ensureCapaciity();
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new HashNode<>(key, value);
        } else {
            HashNode<k, v> cur = table[bucketIndex];
            while (cur.next != null) {
                cur = cur.next;
            }
            HashNode<k, v> t = new HashNode<>(key, value);
            t.pre = cur;
            cur.next = t;
        }
        size++;
        return value;
    }

    private void ensureCapaciity() throws RuntimeException {
        if (size >= capacity * loadFactor) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding Maximum Capacity");
            } else {
                rehash();
            }
        }
    }

    private void rehash() {
        HashNode<k, v> temp[] = table;
        size = 0;
        capacity <<= 1;
        table = new HashNode[capacity];
        for (HashNode<k, v> temp1 : temp) {
            if (temp1 != null) {
                HashNode<k, v> cur = temp1;
                for (; cur != null; cur = cur.next) {
                    put(cur.getKey(), cur.getValue());
                }
            }
        }
    }

    @Override
    public v remove(k key) {

        if (get(key) == null) {
            return null;
        }
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {

            HashNode<k, v> cur = table[bucketIndex];

            for (; cur != null; cur = cur.next) {
                if (cur.getKey().equals(key)) {
                    v ret = cur.getValue();

                    if (cur.equals(table[bucketIndex])) { // removeHead
                        table[bucketIndex] = cur.next;
                        if (cur.next != null) {
                            cur.next.pre = null;
                        }
                    } else if (cur.next == null && cur.pre != null) {
                        cur.pre.next = cur.next;
                    } else {
                        cur.next.pre = cur.pre;
                        cur.pre.next = cur.next;
                    }
                    size--;
                    return ret;

                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<v> values() {

        Set<v> set = new HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                HashNode<k, v> cur = table[i];
                for (; cur != null; cur = cur.next) {
                    set.add(cur.getValue());
                }
            }
        }
        return set;
    }

    @Override
    public String toString() {

        StringBuilder b = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {

                HashNode<k, v> cur = table[i];

                for (; cur != null; cur = cur.next) {
                    b.append(cur.entry);
                }
            }
        }
        b.append("]");
        return b.toString();
    }
}
