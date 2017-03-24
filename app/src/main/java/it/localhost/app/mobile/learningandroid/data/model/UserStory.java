package it.localhost.app.mobile.learningandroid.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 *
 */

public class UserStory extends RealmObject {

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
}
