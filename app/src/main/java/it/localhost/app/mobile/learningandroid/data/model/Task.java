package it.localhost.app.mobile.learningandroid.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 *
 */

public class Task extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    private String subtitle;
    private boolean needed;
    private int type;
    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isNeeded() {
        return needed;
    }

    public void setNeeded(boolean needed) {
        this.needed = needed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeByte(this.needed ? (byte) 1 : (byte) 0);
        dest.writeInt(this.type);
        dest.writeLong(this.timestamp);
    }

    public Task() {
    }

    protected Task(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.subtitle = in.readString();
        this.needed = in.readByte() != 0;
        this.type = in.readInt();
        this.timestamp = in.readLong();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
