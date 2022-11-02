package genericcollections;

import java.util.Stack;

public class Graph {

    private int size;
    private Vertix[] vList;
    private int[][] matrix;
    private int count;

    public Graph(int s) {

        this.size = s;
        this.vList = new Vertix[s];
        this.matrix = new int[s][s];
        count = 0;
    }

    public void addVertix(String a) {
        vList[count++] = new Vertix(a);
    }

    public void addEdgeUn(int begin, int end) {
        matrix[begin][end] = 1;
        matrix[end][begin] = 1;

    }

    public void addEdgeD(int begin, int end) {
        matrix[begin][end] = 1;
    }

    public void addEdgeUnW(int begin, int end, int weight) {
        matrix[begin][end] = weight;
        matrix[end][begin] = weight;
    }

    public void addEdgeDW(int begin, int end, int weight) {
        matrix[begin][end] = weight;
    }

    private void dfs(int i) {  // p v p -- stack --- push -- visited -- print
        Stack<Integer> st = new Stack<>();
        st.push(i);
        vList[i].setIsVisited(true);
        System.out.println(vList[i].getLabel());
        int ver;

        while (!st.isEmpty()) {

            // get an unvisited vertix adjacent to stack top
            ver = getAdjacentUnvisitedVertix(st.peek());

            if (ver == -1) {
                st.pop();
            } else {
                st.push(ver);

                vList[ver].setIsVisited(true);

                System.out.println(vList[ver].getLabel());
            }

        }

        //  reset flags
        for (int j = 0; j < count; j++) {
            vList[j].setIsVisited(false);
        }

    }

    private int getAdjacentUnvisitedVertix(int i) {

        for (int j = 0; j < count; j++) {
            if (matrix[i][j] == 1 && vList[j].getIsVisited() == false) {
                return j;
            }
        }
        return -1;
    }

}
