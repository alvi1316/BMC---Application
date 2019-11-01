package bmc.application;


public class SortComp {
    private int first;
    private int mid;
    private int last;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public SortComp(int first, int mid, int last) {
        this.first = first;
        this.mid = mid;
        this.last = last;
    }
    
}
