package genericcollections;

import java.util.*;

public class Balance_Factor {

    static class Pair {

        TreeNode<Integer> node;
        int bf;
    }

    static ArrayList<Pair> ls = new ArrayList<>();

    private static int calcBalanceFactors(TreeNode<Integer> root) {

        if (root == null) {
            return 0;
        }
        Pair p = new Pair();
        int left = calcBalanceFactors(root.left);
        ls.add(p);
        int right = calcBalanceFactors(root.right);

        p.node = root;
        p.bf = left - right;

        return 1 + Math.max(left, right);

    }

    public static void main(String[] args) {

        //
        TreeNode<Integer> nine = new TreeNode<>(9);
        TreeNode<Integer> five = new TreeNode<>(5);
        TreeNode<Integer> fifteen = new TreeNode<>(15);
        TreeNode<Integer> three = new TreeNode<>(3);
        TreeNode<Integer> six = new TreeNode<>(6);
        TreeNode<Integer> two = new TreeNode<>(2);
        TreeNode<Integer> eighteen = new TreeNode<>(18);
        TreeNode<Integer> eleven = new TreeNode<>(11);
        TreeNode<Integer> thirteen = new TreeNode<>(13);
        TreeNode<Integer> twenty = new TreeNode<>(20);
        TreeNode<Integer> sixteen = new TreeNode<>(16);
        TreeNode<Integer> twentyone = new TreeNode<>(21);
        TreeNode<Integer> twentythree = new TreeNode<>(23);

        nine.left = five;
        nine.right = fifteen;
        five.left = three;
        five.right = six;
        three.left = two;
        fifteen.right = twentyone;
        fifteen.left = eleven;
        eleven.right = thirteen;
        twentyone.left = eighteen;
        twentyone.right = twentythree;
        eighteen.left = sixteen;
        eighteen.right = twenty;

        calcBalanceFactors(nine);

        for (var x : ls) {

            System.out.println("Node: " + x.node.data + "\t\t" + "BalanceFactor: " + x.bf);
        }

    }

}
