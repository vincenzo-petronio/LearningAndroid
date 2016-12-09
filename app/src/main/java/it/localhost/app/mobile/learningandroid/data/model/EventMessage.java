package it.localhost.app.mobile.learningandroid.data.model;

/**
 * Message per Bus
 */
public class EventMessage {

    private String message;

    /**
     *
     * @param message String
     */
    public EventMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
