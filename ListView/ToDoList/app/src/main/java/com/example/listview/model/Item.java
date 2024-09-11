package com.example.listview.model;

public class Item {
    private String text;
    private int imageResId;
    private boolean checked;

    public Item(String text, int imageResId, boolean checked) {
        this.text = text;
        this.imageResId = imageResId;
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setText(String text) { // Correct implementation for setting text
        this.text = text;
    }
}