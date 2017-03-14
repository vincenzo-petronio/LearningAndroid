package it.localhost.app.mobile.learningandroid.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *
 */

public class UserStory extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Task> taskRealmCollection;
    private boolean completed;
}
