package search;

public class SearchDetails {
    int state=0;
    public static final int SEARCHING=1;
    public static final int FOUND=2;
    public static final int CLOSED=3;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
