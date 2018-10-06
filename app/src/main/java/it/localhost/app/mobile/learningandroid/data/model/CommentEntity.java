package it.localhost.app.mobile.learningandroid.data.model;

import android.provider.BaseColumns;

/**
 * @author vincenzo.petronio on 06/10/2018.
 */
public class CommentEntity extends EntityBase
//        implements BaseColumns
{

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
