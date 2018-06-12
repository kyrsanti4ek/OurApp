package com.example.user.ourapp;

public class ChangeEvent {
    boolean isNeedToBeChanged;

    public ChangeEvent(boolean isNeedToBeChanged) {
        this.isNeedToBeChanged = isNeedToBeChanged;
    }

    public boolean isNeedToBeChanged() {
        return isNeedToBeChanged;
    }
}
