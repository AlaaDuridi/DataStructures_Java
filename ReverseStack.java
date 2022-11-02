/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericcollections;

import java.util.Stack;

/**
 *
 * @author USER
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println(s);

        reverse(s);
        System.out.println(s);

    }

    private static void reverse(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        } else {

            Integer t = removeBottom(s);
            reverse(s);
            s.push(t);
        }
    }

    private static Integer removeBottom(Stack<Integer> s) {

        Integer t = s.pop();
        if (s.isEmpty()) {
            return t;
        }

        Integer i = removeBottom(s);
        s.push(t);
        return i;
    }

}
