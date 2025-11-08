package org.example;

public class Action {
    private Boolean isShift;
    private Integer state;

    public Action() {
    }

    public Action(boolean isShift, int state) {
        this.isShift = isShift;
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (state != null) {
            sb.append(isShift ? 's' : 'r');
            sb.append(state);
        }
        return sb.toString();
    }

    void setState(int state) {
        this.state = state;
    }

    void setIsShift(boolean isShift) {
        this.isShift = isShift;
    }
}
