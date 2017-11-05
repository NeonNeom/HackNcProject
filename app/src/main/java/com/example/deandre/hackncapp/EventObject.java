package com.example.deandre.hackncapp;

import java.io.Serializable;

/**
 * Created by Deandre on 11/4/2017.
 */

public class EventObject implements Cloneable,Serializable {
    String name, date;
    private int highlight;

    public EventObject() {
        name = null;
        date = null;
        highlight = 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public int getHighlight() {
        return highlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }
}
