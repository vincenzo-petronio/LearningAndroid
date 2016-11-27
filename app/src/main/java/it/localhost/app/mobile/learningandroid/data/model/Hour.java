package it.localhost.app.mobile.learningandroid.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class Hour implements Parcelable {

    private String hhmm;
    private boolean isOpen;

    public Hour(String hhmm, boolean isOpen) {
        this.hhmm = hhmm;
        this.isOpen = isOpen;
    }

    public Hour(Parcel in) {
        hhmm = in.readString();
        isOpen = in.readByte() != 0;
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(hhmm);
        parcel.writeByte((byte) (isOpen ? 1 : 0));
    }

    public String getHhmm() {
        return hhmm;
    }

    public void setHhmm(String hhmm) {
        this.hhmm = hhmm;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
