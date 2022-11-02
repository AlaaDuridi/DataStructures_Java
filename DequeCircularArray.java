package genericcollections;

public class DequeCircularArray<E> {
    //attributes
    // constructor
    // isFull
    //isEmpty
    // void addFirst
    // void addLast
    // E removeFirst
    //E removeLast
    // E getFirst
    //E getLast
    // void display Or ToString override

    E[] elem;
    int front = -1;
    int rear = -1;
    int size;
    int n;

    public DequeCircularArray(int k) {
        elem = (E[]) new Object[k];
        n = k;
        size = 0;
    }

    public boolean isFull() {
        return front == ((rear + 1) % n);
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void addFirst(E e) {
        if (isFull()) {
            System.out.println("Deque is full");
            return;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else if (front == 0) {
            front = n - 1;
        } else {
            front--;
        }
        elem[front] = e;
        size++;
    }

    public void addLast(E e) {
        if (isFull()) {
            System.out.println("Deque is full");
            return;
        }

    }

}
