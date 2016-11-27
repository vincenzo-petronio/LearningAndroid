package it.localhost.app.mobile.learningandroid.data.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import android.os.Parcel;

import java.util.List;


/**
 *
 */

public class Day extends ExpandableGroup<Hour> {

    private String dd;

    public Day(String title, List<Hour> items) {
        super(title, items);
    }

    public Day(String title, List<Hour> items, String day) {
        super(title, items);
        this.dd = day;
    }

    protected Day(Parcel in) {
        super(in);
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }
}
