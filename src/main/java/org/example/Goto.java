package org.example;

public class Goto {
    private Integer state;

    public Goto() {
    }

    public Goto(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if (state != null) {
            return state.toString();
        }
        return "";
    }

    public void setState(int state) {
        this.state = state;
    }
}
