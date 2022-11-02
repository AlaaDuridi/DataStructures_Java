/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import java.util.Stack;

public class MyQueue2<E> {

    private Stack<E> q;
    private int size = 0;

    public MyQueue2() {
        q = new Stack<>();
    }

    public void enqueue(E e) {
        q.push(e);
        size++;
    }

    public E dequeue() {
        size--;
        return removeBottom();

    }

    private E removeBottom() {
        E temp = q.pop();
        if (q.isEmpty()) {
            return temp;
        }
        E i = dequeue();
        q.push(temp);
        return i;
    }

    public String toString() {
        return q.toString();
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {

        MyQueue2<Integer> m = new MyQueue2<>();
        m.enqueue(4);
        m.enqueue(5);
        m.enqueue(6);
        m.enqueue(7);
        System.out.println(m);
        m.dequeue();
        System.out.println(m);
        m.enqueue(8);
        System.out.println(m);

    }
}
