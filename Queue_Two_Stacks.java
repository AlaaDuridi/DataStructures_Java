/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import java.util.Stack;

public class Queue_Two_Stacks<E> {

    Stack<E> q;
    int size = 0;

    public Queue_Two_Stacks() {
        q = new Stack<>();
        size = 0;
    }

    public void enqueue(E e) {
        q.push(e);
        size++;
    }

    public E dequeue() {
        Stack<E> t = new Stack<>();
        while (!q.isEmpty()) {
            t.push(q.pop());
        }

        E ret = t.pop();

        while (!t.isEmpty()) {
            q.push(t.pop());
        }
        size--;
        return ret;

    }

    public int size() {
        return size;
    }

    public String toString() {

        return q.toString();

    }

}
