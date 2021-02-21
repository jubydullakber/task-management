package com.jubydull.pt.projecttask.enums;

public enum Status {

    OPEN(0), IN_PROGRESS(1), CLOSED(3);
    private int val;

    Status(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
