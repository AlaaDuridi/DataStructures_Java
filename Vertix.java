package genericcollections;

public class Vertix {

    private String label;

    private boolean isVisited;

    public Vertix(String a) {
        this.label = a;
        isVisited = false;
    }

    public void setLabel(String a) {
        this.label = a;
    }

    public void setIsVisited(boolean b) {
        this.isVisited = b;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean getIsVisited() {
        return this.isVisited;
    }

}
