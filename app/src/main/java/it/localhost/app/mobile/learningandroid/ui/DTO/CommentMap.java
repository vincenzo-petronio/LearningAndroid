package it.localhost.app.mobile.learningandroid.ui.DTO;

import org.modelmapper.PropertyMap;

import it.localhost.app.mobile.learningandroid.data.model.CommentEntity;

/**
 * @author vincenzo.petronio on 08/10/2018.
 */
public class CommentMap extends PropertyMap<CommentDTO, CommentEntity> {
    @Override
    protected void configure() {
        map().setBody(source.getText());
    }
}
