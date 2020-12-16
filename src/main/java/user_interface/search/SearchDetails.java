package user_interface.search;

public class SearchDetails {
    int state=0;
    public static final int SEARCHING=1;
    public static final int FOUND=2;
    public static final int CLOSED=3;
    public static final int ADD=4;
    public static final int BACKUP=5;
    public static final int RESTORE=6;
    public static final int SHOW_ALL_RECORDS=7;
    public static final int SHOW_UNAVAILABLE_RECORDS=8;

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
