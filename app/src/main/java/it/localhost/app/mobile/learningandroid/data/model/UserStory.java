package it.localhost.app.mobile.learningandroid.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 *
 */

public class UserStory extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    @Required
    private String name;
    private RealmList<Task> taskRealmCollection;
    private boolean completed;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Task> getTaskRealmCollection() {
        return taskRealmCollection;
    }

    public void setTaskRealmCollection(RealmList<Task> taskRealmCollection) {
        this.taskRealmCollection = taskRealmCollection;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeList(this.taskRealmCollection);
        dest.writeByte(this.completed ? (byte) 1 : (byte) 0);
    }

    public UserStory() {
    }

    protected UserStory(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.taskRealmCollection = new RealmList<>();
        in.readList(this.taskRealmCollection, Task.class.getClassLoader());
        this.completed = in.readByte() != 0;
    }

    public static final Creator<UserStory> CREATOR = new Creator<UserStory>() {
        @Override
        public UserStory createFromParcel(Parcel source) {
            return new UserStory(source);
        }

        @Override
        public UserStory[] newArray(int size) {
            return new UserStory[size];
        }
    };
}
